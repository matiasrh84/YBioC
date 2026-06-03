package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Anticipo;
import com.ybc.ybioq.repository.local.AnticipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnticipoService extends AbstractCrudService<Anticipo, Integer> {

    private final AnticipoRepository anticipoRepository;

    public AnticipoService(AnticipoRepository repository) {
        super(repository);
        this.anticipoRepository = repository;
    }

    public List<Anticipo> findByOrden(Integer idOrden) {
        return anticipoRepository.findByIdOrdenOrderByFechaDesc(idOrden);
    }
}
