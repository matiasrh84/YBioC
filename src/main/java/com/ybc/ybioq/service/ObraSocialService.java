package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.ObraSocial;
import com.ybc.ybioq.repository.local.ObraSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraSocialService {

    @Autowired
    private ObraSocialRepository obraSocialRepository;

    public List<ObraSocial> getAllObraSocial() {
        return obraSocialRepository.findAll();
    }

    public Optional<ObraSocial> getObraSocialByCodigoAndNombre(String codigo, String nombre) {
        return obraSocialRepository.findObraSocialByIntCodigoObraSocialAndNombreObraSocial(codigo, nombre);
    }
}
