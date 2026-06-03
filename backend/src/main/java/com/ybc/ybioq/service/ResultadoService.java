package com.ybc.ybioq.service;

import com.ybc.ybioq.api.dto.ResultadoDto;
import com.ybc.ybioq.entity.local.Analisis;
import com.ybc.ybioq.entity.local.Practica;
import com.ybc.ybioq.entity.local.Resultado;
import com.ybc.ybioq.entity.local.ResultadoId;
import com.ybc.ybioq.repository.local.AnalisisRepository;
import com.ybc.ybioq.repository.local.PracticaRepository;
import com.ybc.ybioq.repository.local.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoService extends AbstractCrudService<Resultado, ResultadoId> {

    private final ResultadoRepository resultadoRepository;
    private final AnalisisRepository analisisRepository;
    private final PracticaRepository practicaRepository;

    public ResultadoService(ResultadoRepository repository,
                            AnalisisRepository analisisRepository,
                            PracticaRepository practicaRepository) {
        super(repository);
        this.resultadoRepository = repository;
        this.analisisRepository = analisisRepository;
        this.practicaRepository = practicaRepository;
    }

    public List<ResultadoDto> findByOrdenId(Integer idOrden) {
        return resultadoRepository.findByOrdenId(idOrden).stream()
                .map(this::toDto)
                .toList();
    }

    private ResultadoDto toDto(Resultado r) {
        Analisis analisis = analisisRepository.findById(r.getId().getIdAnalisis()).orElse(null);
        Practica practica = practicaRepository.findById(r.getId().getIdPracticas()).orElse(null);

        return new ResultadoDto(
                r.getId().getIdAnalisis(),
                r.getId().getIdPracticas(),
                r.getId().getIdOrdenes(),
                r.getId().getIdUsuarios(),
                r.getResultado(),
                r.getObservacion(),
                r.getEstadoImprime(),
                r.getImprimirNombre(),
                analisis != null ? analisis.getNombre() : null,
                analisis != null ? analisis.getTipoResultado() : null,
                analisis != null ? analisis.getUnidad() : null,
                analisis != null ? analisis.getValoresReferencia() : null,
                practica != null ? practica.getDeterminacion() : null,
                practica != null ? practica.getCodigo() : null
        );
    }
}
