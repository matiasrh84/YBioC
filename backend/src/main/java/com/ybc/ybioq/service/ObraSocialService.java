package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Localidad;
import com.ybc.ybioq.entity.local.ObraSocial;
import com.ybc.ybioq.entity.local.Provincia;
import com.ybc.ybioq.repository.local.LocalidadRepository;
import com.ybc.ybioq.repository.local.ObraSocialRepository;
import com.ybc.ybioq.repository.local.ProvinciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ObraSocialService extends AbstractCrudService<ObraSocial, Integer> {

    private static final int DEFAULT_LOCALIDAD_ID = 2358;
    private static final int DEFAULT_PROVINCIA_ID = 25;

    private final ObraSocialRepository obraSocialRepository;
    private final LocalidadRepository localidadRepository;
    private final ProvinciaRepository provinciaRepository;

    public ObraSocialService(ObraSocialRepository obraSocialRepository,
                             LocalidadRepository localidadRepository,
                             ProvinciaRepository provinciaRepository) {
        super(obraSocialRepository);
        this.obraSocialRepository = obraSocialRepository;
        this.localidadRepository = localidadRepository;
        this.provinciaRepository = provinciaRepository;
    }

    public List<ObraSocial> getAllObraSocial() {
        return findAll();
    }

    public Optional<ObraSocial> getObraSocialByCodigoAndNombre(String codigo, String nombre) {
        return obraSocialRepository.findObraSocialByIntCodigoAndNombre(codigo, nombre);
    }

    @Transactional
    public ObraSocial guardar(ObraSocial os) {
        if (os.getCodigo() == null || os.getCodigo().isBlank()) {
            throw new IllegalArgumentException("El código de la obra social es obligatorio.");
        }
        if (os.getRazonSocial() == null || os.getRazonSocial().isBlank()) {
            throw new IllegalArgumentException("La razón social es obligatoria.");
        }
        if (obraSocialRepository.existsByCodigoAndIdNot(os.getCodigo(), os.getId() == null ? -1 : os.getId())) {
            throw new IllegalArgumentException("Ya existe una obra social con ese código.");
        }
        if (os.getLocalidad() == null) {
            Localidad localidad = localidadRepository.findById(DEFAULT_LOCALIDAD_ID)
                    .orElseThrow(() -> new RuntimeException("Localidad por defecto no encontrada."));
            os.setLocalidad(localidad);
        }
        if (os.getProvincia() == null) {
            Provincia provincia = provinciaRepository.findById(DEFAULT_PROVINCIA_ID)
                    .orElseThrow(() -> new RuntimeException("Provincia por defecto no encontrada."));
            os.setProvincia(provincia);
        }
        return obraSocialRepository.save(os);
    }
}
