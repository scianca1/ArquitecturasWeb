package com.example.microviaje.Services;

import com.example.microviaje.Repositories.ViajeRepository;
import com.example.microviaje.dtos.*;
import com.example.microviaje.dtos.CuentaDto;
import com.example.microviaje.entitys.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class    ViajeService {

    @Autowired
    private ViajeRepository repositorio;

    private RestTemplate rest;
    
    public ViajeService(ViajeRepository r) {
        rest= new RestTemplate();
        this.repositorio=r;
    }

    public List<ViajeDto> findAll() throws Exception {
        return this.repositorio.findAll().stream().map(ViajeDto::new).toList();
    }

    public List<ViajeDto> viajesByAnio(Integer anio) throws Exception {
        List<Viaje> v= this.repositorio.viajeByAnio(anio);
        List<ViajeDto> respuesta= new ArrayList<>();
        for(Viaje viaje:v){
            ViajeDto dto= new ViajeDto(viaje);
            respuesta.add(dto);
        }
        return respuesta;
    }

    public List<ViajeDto> viajesPorAñoEntreMeses(Integer anio,Integer mes1, Integer mes2) throws Exception {
        List<Viaje> v= this.repositorio.viajesPorAñoEntreMeses(anio,mes1,mes2);
        List<ViajeDto> respuesta= new ArrayList<>();
        for(Viaje viaje:v){
            ViajeDto dto= new ViajeDto(viaje);
            respuesta.add(dto);
        }
        return respuesta;
    }

    public ViajeDto findById(Long id) throws Exception {
        return this.repositorio.findById(id).map(ViajeDto::new).orElseThrow(
                () -> new IllegalArgumentException("ID de estacion invalido: " + id));
    }

    public ViajeDto iniciarViaje(ViajeDto viaje) throws Exception {

        //USUARIO
        ResponseEntity<UsuarioDto> usuario=this.rest.getForEntity("http://localhost:8003/usuario/id/"+viaje.getIdUsuario(), UsuarioDto.class);

        //CUENTA
        ResponseEntity<CuentaDto> cuenta=this.rest.getForEntity("http://localhost:8003/usuario/IdUsuario/"+ viaje.getIdUsuario() + "/IdCuenta/" + viaje.getIdCuenta(), CuentaDto.class);
        CuentaDto cuentaDto= cuenta.getBody();

        //MONOPATIN
        ResponseEntity<MonopatinDto> monopatin = rest.getForEntity("http://localhost:8001/monopatin/id/" + viaje.getIdMonopatin(), MonopatinDto.class);

        //PARADA
        ResponseEntity<ParadaDto> paradaOrigen = rest.getForEntity("http://localhost:8001/paradas/id/" + viaje.getIdParadaOrigen(), ParadaDto.class);
        ResponseEntity<ParadaDto> paradaDestino = rest.getForEntity("http://localhost:8001/paradas/id/" + viaje.getIdParadaDestino(), ParadaDto.class);
        System.out.println("service");
        //CONSULTO POR ID USUARIO E ID MONOPATIN
        if (usuario.getStatusCode() == HttpStatus.OK && monopatin.getStatusCode() == HttpStatus.OK && monopatin.getBody().isHabilitado()) {
            //CONSULTO POR SALDO EN CUENTA
            if(cuenta.getStatusCode()== HttpStatus.OK && cuentaDto.getSaldo()>0 && cuentaDto.isAnulada()==false){
                //CONSULTO POR ID PARADA ORIGEN Y DESTINO
                if(paradaOrigen.getStatusCode()==HttpStatus.OK && paradaDestino.getStatusCode()==HttpStatus.OK){

                    Viaje viajeNuevo= new Viaje(viaje.getIdUsuario(), viaje.getIdMonopatin(),viaje.getIdCuenta(), viaje.getIdParadaOrigen(), viaje.getIdParadaDestino());

                    ViajeDto respuesta= new ViajeDto(repositorio.save(viajeNuevo));
                    return respuesta;
                }
                else{
                    throw new IllegalArgumentException("Alguna de las paradas no es valida");
                }

            }
            else{
                throw new IllegalArgumentException("La cuenta no tiene saldo");
            }
        }
        else {
            throw new IllegalArgumentException("Id de usuario o  Id monopatin invalido");
        }
    }

    public ViajeDto finalizarViaje(Long id) throws Exception {

        //Ver si existe el id
        Optional<Viaje> optional= this.repositorio.findById(id);

        if(optional.isPresent()){
            Viaje viaje= optional.get();
            viaje.setHoraFin(LocalTime.now());
            viaje.setFechaFin(LocalDate.now());

            ResponseEntity<AdministradorDto> tarifa=this.rest.getForEntity("http://localhost:8002/administrador/tarifas", AdministradorDto.class);
            AdministradorDto adminDto= tarifa.getBody();
            System.out.println(adminDto.getTarifa());

            //Monopatin
            ResponseEntity<MonopatinDto> monopatin = rest.getForEntity("http://localhost:8001/monopatin/id/" + viaje.getIdMonopatin(), MonopatinDto.class);
            MonopatinDto monopatinDto= monopatin.getBody();

            //Ubicacion monopatin
            long ubiXMonopatin= monopatinDto.getX();
            long ubiYMonopatin= monopatinDto.getY();

            //Parada destino de viaje
            ResponseEntity<ParadaDto> paradaDestino = rest.getForEntity("http://localhost:8001/paradas/id/" + viaje.getIdParadaDestino(), ParadaDto.class);
            ParadaDto paradaDto= paradaDestino.getBody();

            //Ubicacion parada destino
            long ubiXDestino= paradaDto.getX();
            long ubiYDestino= paradaDto.getY();

            int valorViaje=0;

            if(ubiXDestino==ubiXMonopatin && ubiYDestino==ubiYMonopatin){

                if(viaje.getPausa()==-1){
                    Duration duracionTarifaNormal= Duration.between(viaje.getHoraInicio(), viaje.getHoraInicioPausa().plusMinutes(15));
                    int minutosNormal= (int)duracionTarifaNormal.toMinutes();
                    Duration duracionTarifaAumentada= Duration.between(viaje.getHoraInicioPausa().plusMinutes(15), viaje.getHoraFin());
                    int minutosExtendidos= (int)duracionTarifaAumentada.toMinutes();
                    valorViaje= (minutosNormal*adminDto.getTarifa() + minutosExtendidos*adminDto.getTarifaPorPausaExtensa());
                }
                else{
                    //Calcular duracion en minutos del viaje
                    Duration duracionViaje= Duration.between(viaje.getHoraInicio(), viaje.getHoraFin());
                    int minutosViaje= (int)duracionViaje.toMinutes();
                    valorViaje= adminDto.getTarifa()*minutosViaje;
                }

                //PARADA ORIGEN
                ResponseEntity<ParadaDto> paradaOrigen = rest.getForEntity("http://localhost:8001/paradas/id/" + viaje.getIdParadaOrigen(), ParadaDto.class);
                ParadaDto paradaDtoOrigen= paradaOrigen.getBody();


                long ubiXOrigen= paradaDtoOrigen.getX();
                long ubiYOrigen= paradaDtoOrigen.getY();
                double kmX= ubiXOrigen-ubiXDestino;

                if(kmX<0){
                    kmX= kmX*-1;
                }
                double kmY= ubiYOrigen-ubiYDestino;
                if(kmY<0){
                    kmY= kmY*-1;
                }

                double km= kmX+ kmY;
                System.out.println(km);
                HttpHeaders cabecera = new HttpHeaders();
                HttpEntity<Void> solicitud1 = new HttpEntity<>(cabecera);
                ResponseEntity<MonopatinDto> response = rest.exchange(
                        "http://localhost:8001/monopatin/addKilometros/id/" + viaje.getIdMonopatin() + "/km/" + km,
                        HttpMethod.PUT,
                        solicitud1,
                        new ParameterizedTypeReference<>() {});
                cabecera.setContentType(MediaType.APPLICATION_JSON);
                System.out.println(response.getBody());

                viaje.setValorViaje(valorViaje);
                ViajeDto respuesta= new ViajeDto(repositorio.save(viaje));
                return respuesta;
            }
            else {
                throw new IllegalArgumentException("La ubicacion del monopatin no es la que indicaste como parada destino al iniciar tu viaje");
            }
        }
        else{
            return null;
        }

    }


    public ViajeDto pausarViaje(Long id){
        Viaje viaje = repositorio.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id invalido"));
        viaje.setViajePausado(true);
        LocalTime horaPausa= LocalTime.now();
        viaje.setHoraInicioPausa(horaPausa);
        ViajeDto respuesta= new ViajeDto(repositorio.save(viaje));
        return respuesta;
    }

    public ViajeDto despausarViaje(Long id){
        Viaje viaje = repositorio.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id invalido"));
        viaje.setViajePausado(false);
        LocalTime horaDespausa= LocalTime.now();
        viaje.setHoraFinPausa(horaDespausa);

        Duration duracionPausa= Duration.between(viaje.getHoraInicioPausa(), horaDespausa);
        long minutosPausa= duracionPausa.toMinutes();

        if(viaje.getPausa()<minutosPausa){
            viaje.setPausa(-1);
        }

        ViajeDto respuesta= new ViajeDto(repositorio.save(viaje));
        return respuesta;
    }

    public ViajeDto update(Long id, ViajeDto viajeDto) {
        Viaje viaje = repositorio.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id invalido"));
        viaje.setIdUsuario(viajeDto.getIdUsuario());
        viaje.setIdMonopatin(viajeDto.getIdMonopatin());
        viaje.setPausa(viajeDto.getPausa());
        viaje.setFechaInicio(viajeDto.getFechaInicio());
        viaje.setFechaFin(viajeDto.getFechaFin());
        viaje.setHoraFin(viajeDto.getHoraFin());
        viaje.setHoraInicio((viajeDto.getHoraInicio()));
        viaje.setIdCuenta(viajeDto.getIdCuenta());
        viaje.setIdParadaOrigen(viajeDto.getIdParadaOrigen());
        viaje.setIdParadaDestino(viajeDto.getIdParadaDestino());
        repositorio.save(viaje);
        return viajeDto;
    }


    public void delete(Long id) {
        repositorio.delete(repositorio.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id invalido")));
    }


}