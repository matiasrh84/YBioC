package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.ActualizarResultadoRequest;
import com.ybc.ybioq.api.dto.ResultadoDto;
import com.ybc.ybioq.entity.local.Resultado;
import com.ybc.ybioq.entity.local.ResultadoId;
import com.ybc.ybioq.service.ResultadoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoApiController {

    private final ResultadoService resultadoService;

    public ResultadoApiController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping("/orden/{idOrden}")
    public List<ResultadoDto> findByOrden(@PathVariable Integer idOrden) {
        return resultadoService.findByOrdenId(idOrden);
    }

    @PutMapping
    public ResultadoDto actualizar(
            @RequestParam Integer idAnalisis,
            @RequestParam Integer idPracticas,
            @RequestParam Integer idOrdenes,
            @RequestParam Integer idUsuarios,
            @RequestBody ActualizarResultadoRequest request) {

        ResultadoId id = new ResultadoId();
        id.setIdAnalisis(idAnalisis);
        id.setIdPracticas(idPracticas);
        id.setIdOrdenes(idOrdenes);
        id.setIdUsuarios(idUsuarios);

        Resultado resultado = resultadoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado no encontrado."));

        resultado.setResultado(request.resultado());
        resultado.setObservacion(request.observacion());
        resultado.setEstadoImprime(request.estadoImprime());
        resultado.setImprimirNombre(request.imprimirNombre());

        Resultado guardado = resultadoService.save(resultado);
        return toDto(guardado);
    }

    private ResultadoDto toDto(Resultado r) {
        return resultadoService.findByOrdenId(r.getId().getIdOrdenes()).stream()
                .filter(d -> d.idAnalisis().equals(r.getId().getIdAnalisis())
                        && d.idPracticas().equals(r.getId().getIdPracticas()))
                .findFirst()
                .orElse(null);
    }
}
