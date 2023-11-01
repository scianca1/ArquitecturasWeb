package com.example.microadmin.servicios;

import com.example.microadmin.dtos.*;
import com.example.microadmin.dtos.reporteDto.ReportePorCantViajes;
import com.example.microadmin.entitys.Administrador;
import com.example.microadmin.repositorios.AdminRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServicio implements BaseServicio<AdminDto>{

    private AdminRepositorio repositorio;

    @Autowired
    private RestTemplate monopatinClienteRest;

    public AdminServicio (AdminRepositorio ar){
        this.repositorio= ar;
    }

    @Transactional
    public AdminDto getAdmin() {
        return new AdminDto(repositorio.getAdmin());
    }

    @Transactional
    public MonopatinIdDto editarMonopatin (MonopatinIdDto m){
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<MonopatinIdDto> solicitud1 = new HttpEntity<>(m, cabecera);
        ResponseEntity<MonopatinIdDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001//monopatin/put",
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    @Transactional
    public MonopatinDto editarMantenimiento (Long idMonopatin, boolean estado){
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<Void> solicitud1 = new HttpEntity<>(cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin/id/" + idMonopatin + "/habilitado/" + estado,
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    @Transactional
    public MonopatinDto agregarMonopatin(MonopatinDto monopatin) {
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<MonopatinDto> objetoMonopatin = new HttpEntity<>(monopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin",
                HttpMethod.POST,
                objetoMonopatin,
                new ParameterizedTypeReference<>() {}
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON); //El cuerpo de la solicitud se envia en formato JSON

        return respuesta.getBody();
    }

    @Transactional
    public MonopatinDto eliminarMonopatin(Long idMonopatin) throws Exception {
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<Long> objetoMonopatin = new HttpEntity<>(idMonopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin/delete/" + idMonopatin,
                HttpMethod.DELETE,
                objetoMonopatin,
                new ParameterizedTypeReference<>() {}
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    @Transactional
    public CuentaDto anularCuenta (Long id, boolean estado){
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<CuentaDto> solicitud1 = new HttpEntity<>(cabecera);
        ResponseEntity<CuentaDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8003/cuenta/" + id + "/anular/" + estado,
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    @Transactional
    public AdminDto actualizarTarifas(int tarifaNormal, int tarifaPorPausaExtensa){
        AdminDto admin= this.getAdmin();
        if (admin.getFechaActualizacionPrecios().isAfter(LocalDate.now())) {
            repositorio.actualizarTarifas(tarifaNormal, tarifaPorPausaExtensa);
            return admin;
        } else {
            throw new IllegalArgumentException("Usted quiere actualizar los precios en una fecha invalida. Revise que dia se actualizan los precios");
        }

    }

    @Transactional
    public AdminDto getTarifas(){
        Administrador administrador= repositorio.getTarifas();
        return new AdminDto(administrador);
    }

    public String totalFacturado(Integer mes1, Integer mes2) {
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<ViajeDto> solicitud = new HttpEntity<>(cabecera);
        ResponseEntity<List<ViajeDto>> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8004/viaje/viajesEntreMeses/" + mes1 + "/" + mes2,
                HttpMethod.GET,
                solicitud,
                new ParameterizedTypeReference<>() {}
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        List<ViajeDto> lista = respuesta.getBody();
        int contador = 0;
        for (ViajeDto viaje : lista){
            contador += viaje.getValorViaje();
        }
        String total = "El total facturado entre " + mes1 + " y " + mes2 + " es de $" + contador ;
        return total;
    }

    @Override
    public List<AdminDto> findAll() throws Exception {
        return null;
    }

    @Transactional
    @Override
    public AdminDto findById(Long id) {
        return repositorio.findById(id).map(AdminDto::new).orElseThrow(
                ()->new IllegalArgumentException("ID invalido:"+id)
        );
    }

    @Transactional
    @Override
    public AdminDto save(AdminDto adminDto) throws Exception {
        Administrador admin = new Administrador(adminDto.getId(), adminDto.getTarifa(), adminDto.getTarifaPorPausaExtensa(), adminDto.getFechaActualizacionPrecios());
        this.repositorio.save(admin);
        return new AdminDto(admin);
    }



}
