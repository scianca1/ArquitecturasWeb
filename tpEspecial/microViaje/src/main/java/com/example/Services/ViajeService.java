package com.example.Services;

import com.example.Repositories.ViajeRepository;
import com.example.dtos.*;
import com.example.entitys.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository repositorio;

    private RestTemplate rest;
    
    public ViajeService() {
        rest= new RestTemplate();
    }

    public List<ViajeDto> findAll() throws Exception {
        return this.repositorio.findAll().stream().map(ViajeDto::new).toList();
    }

    public ViajeDto findById(Long id) throws Exception {
        return this.repositorio.findById(id).map(ViajeDto::new).orElseThrow(
                () -> new IllegalArgumentException("ID de estacion invalido: " + id));
    }

    public ViajeDto iniciarViaje(ViajeDto viaje) throws Exception {
        //USUARIO
        ResponseEntity<UsuarioDto> usuario=this.rest.getForEntity("http://localhost:8003/usuario/id/"+viaje.getIdUsuario(), UsuarioDto.class);

        //CUENTA
        ResponseEntity<CuentaDto> cuenta=this.rest.getForEntity("http://localhost:8003/usuario/IdUsuario/"+ viaje.getIdUsuario() + "IdCuenta/" + viaje.getIdCuenta(), CuentaDto.class);
        CuentaDto cuentaDto= cuenta.getBody();

        //MONOPATIN
        ResponseEntity<MonopatinDto> monopatin = rest.getForEntity("http://localhost:8001/monopatin/id/" + viaje.getIdMonopatin(), MonopatinDto.class);

        //PARADA
        ResponseEntity<ParadaDto> paradaOrigen = rest.getForEntity("http://localhost:8001/parada/id/" + viaje.getIdParadaOrigen(), ParadaDto.class);
        ResponseEntity<ParadaDto> paradaDestino = rest.getForEntity("http://localhost:8001/parada/id/" + viaje.getIdParadaDestino(), ParadaDto.class);

        //CONSULTO POR ID USUARIO E ID MONOPATIN
        if (usuario.getStatusCode() == HttpStatus.OK && monopatin.getStatusCode() == HttpStatus.OK) {
            //CONSULTO POR SALDO EN CUENTA
            if(cuenta.getStatusCode()== HttpStatus.OK && cuentaDto.getSaldo()>0 && cuentaDto.isAnulada()!=false){
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

    public void finalizarViaje(Long id) throws Exception {
        //Ver si existe el id
        Viaje viaje = repositorio.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id invalido"));
        viaje.setHoraFin(LocalTime.now());

        //Tarifa
        ResponseEntity<AdministradorDto> tarifa=this.rest.getForEntity("http://localhost:8002/administrador/tarifas", AdministradorDto.class);
        //Monopatin
        ResponseEntity<MonopatinDto> monopatin = rest.getForEntity("http://localhost:8001/monopatin/id" + viaje.getIdMonopatin(), MonopatinDto.class);

        //Parada destino de viaje
        ResponseEntity<ParadaDto> paradaDestino = rest.getForEntity("http://localhost:8001/parada/id/" + viaje.getIdParadaDestino(), ParadaDto.class);
        ParadaDto paradaDto= paradaDestino.getBody();
        long ubiXDestino= paradaDto.getX();
        long ubiYDestino= paradaDto.getY();
        //Ubicacion monopatin
        long ubiXMonopatin= monopatin.getBody().getX();
        long ubiYMonopatin= monopatin.getBody().getY();



        //Calcular duracion en minutos del viaje
        Duration duracionViaje= Duration.between(viaje.getHoraInicio(), viaje.getHoraFin());
        long minutosViaje= duracionViaje.toMinutes();



        //ver si existe el id de viaje
        //calcular valor viaje con tarifa de admin y con tiempo calculado por mi
        //corroborar que la ubicacion del monopatin sea igual que la parada destino
        //Modificar valores de viaje: fecha de finalizacion, hora de finalizacion, kmRecorridos,valorViaje
        //Pasar valor de viaje a Cuenta
    }

    public void update(Long id, ViajeDto viajeDto) {
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
    }


    public void delete(Long id) {
        repositorio.delete(repositorio.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id invalido")));
    }


}