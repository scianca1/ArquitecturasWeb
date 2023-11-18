package com.example.microadmin.servicios;

import com.example.microadmin.dtos.*;
import com.example.microadmin.dtos.reporteDto.ReportePorCantViajes;
import com.example.microadmin.entitys.Administrador;
import com.example.microadmin.repositorios.AdminRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServicio implements BaseServicio<AdminDto>{

    private AdminRepositorio repositorio;

    @Autowired
    private RestTemplate monopatinClienteRest;

    public AdminServicio (AdminRepositorio ar){
        this.repositorio= ar;
    }



    @Transactional(readOnly = false)
    public MonopatinIdDto editarMonopatin (MonopatinIdDto m, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Authorization", token);
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MonopatinIdDto> solicitud1 = new HttpEntity<MonopatinIdDto>(m, cabecera);
        ResponseEntity<MonopatinIdDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001//monopatin/put",
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});

        return respuesta.getBody();
    }

    @Transactional(readOnly = false)
    public MonopatinDto editarMantenimiento (String idMonopatin, boolean estado, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Authorization", token);
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> solicitud1 = new HttpEntity<String>(idMonopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin/id/" + idMonopatin + "/habilitado/" + estado,
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    @Transactional(readOnly = false)
    public MonopatinDto agregarMonopatin(MonopatinDto monopatin, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Authorization", token);
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MonopatinDto> objetoMonopatin = new HttpEntity<MonopatinDto>(monopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin",
                HttpMethod.POST,
                objetoMonopatin,
                new ParameterizedTypeReference<>() {}
        );
        return respuesta.getBody();
    }

    @Transactional(readOnly = false)
    public MonopatinDto eliminarMonopatin(String idMonopatin, HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Authorization", token);
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> objetoMonopatin = new HttpEntity<String>(idMonopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin/delete/" + idMonopatin,
                HttpMethod.DELETE,
                objetoMonopatin,
                new ParameterizedTypeReference<>() {}
        );
        return respuesta.getBody();
    }

    @Transactional(readOnly = false)
    public CuentaDto anularCuenta (Long id, boolean estado, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Authorization", token);
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> solicitud1 = new HttpEntity<Long>(id,cabecera);
        ResponseEntity<CuentaDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8003/cuenta/idCuenta/" + id + "/estado/" + estado,
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});

        return respuesta.getBody();
    }

    @Transactional(readOnly = false)
        public AdminDto actualizarTarifas(Integer tarifaNormal, Integer tarifaPorPausaExtensa, HttpServletRequest request){
        Optional<Administrador> admin = repositorio.getAdmin();
        Administrador administrador= admin.get();
        administrador.setTarifa(tarifaNormal);
        administrador.setTarifaPorPausaExtensa(tarifaPorPausaExtensa);
        AdminDto dto = new AdminDto(repositorio.save(administrador));
        return dto;
    }

    @Transactional(readOnly = true)
    public AdminDto getAdmin(){
        Optional<Administrador> aux= repositorio.getAdmin();
        if(aux.isPresent()) {
            Administrador administrador= aux.get();
            return new AdminDto(administrador);
        }
       return null;
    }
    //probar cuando arregle viaje
    @Transactional(readOnly = true)
    public String totalFacturado(Integer mes1, Integer mes2, Integer anio, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Authorization", token);
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> solicitud = new HttpEntity<Long>(cabecera);
        ResponseEntity<List<ViajeDto>> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8004/viaje/viajesPorAnioEntreMeses/anio/" + anio + "/mes1/" + mes1 + "/mes2/" + mes2,
                HttpMethod.GET,
                solicitud,
                new ParameterizedTypeReference<>() {}
        );
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

    @Transactional(readOnly = true)
    @Override
    public AdminDto findById(Long id) {
        return repositorio.findById(id).map(AdminDto::new).orElseThrow(
                ()->new IllegalArgumentException("ID invalido:"+id)
        );
    }

    @Transactional(readOnly = false)
    @Override
    public AdminDto save(AdminDto adminDto) throws Exception {
        Administrador admin = new Administrador(adminDto.getId(), adminDto.getTarifa(), adminDto.getTarifaPorPausaExtensa());
        this.repositorio.save(admin);
        return new AdminDto(admin);
    }



}
