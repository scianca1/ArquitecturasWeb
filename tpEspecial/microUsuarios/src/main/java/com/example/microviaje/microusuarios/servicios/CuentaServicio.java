package com.example.microviaje.microusuarios.servicios;

import com.example.microviaje.microusuarios.dtos.CuentaDto;
import com.example.microviaje.microusuarios.entitys.Cuenta;
import com.example.microviaje.microusuarios.repositorios.CuentaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServicio implements BaseServicio<CuentaDto>{
    private CuentaRepositorio repositorio;

    public CuentaServicio(CuentaRepositorio ur){
        this.repositorio= ur;
    }
    @Override
    public List<CuentaDto> findAll() throws Exception {
        return null;
    }

    @Override
    public CuentaDto findById(Long id) throws Exception {
        return null;
    }

    @Override
    public CuentaDto save(CuentaDto c) throws Exception {
        Cuenta cuenta = new Cuenta(c.isAnulada(), c.getCuentaMercadoPago(), c.getFechaDeAlta(), c.getSaldo());
        Cuenta aux = repositorio.save(cuenta);
        return new CuentaDto(cuenta);
    }

    @Override
    public CuentaDto delete(Long id) throws Exception {
        return null;
    }


    public CuentaDto setAnulada(long idCuenta, boolean anulada) {
        Optional<Cuenta> opC=repositorio.findById(idCuenta);
        if(opC.isPresent()){
            Cuenta c=opC.get();
            c.setAnulada(anulada);
            CuentaDto cdto= new CuentaDto(c);
            repositorio.save(c);
            return cdto;
        }
        return null;
    }
}
