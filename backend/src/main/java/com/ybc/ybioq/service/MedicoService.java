package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.entity.local.Medico;
import com.ybc.ybioq.repository.local.EspecialidadRepository;
import com.ybc.ybioq.repository.local.MedicoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService extends AbstractCrudService<Medico, Integer> {

    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;

    public MedicoService(MedicoRepository medicoRepository, EspecialidadRepository especialidadRepository) {
        super(medicoRepository);
        this.medicoRepository = medicoRepository;
        this.especialidadRepository = especialidadRepository;
    }

    public List<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAllByEstadoIsTrueOrderByNombreAsc();
    }

    public List<Medico> obtenerMedicosConEspecialidades() {
        return medicoRepository.findMedicosConEspecialidades();
    }

    public Medico guardarMedico(Medico medico) {
        return save(medico);
    }

    public int obtenerMaxId() {
        return medicoRepository.findMaxId();
    }

    public Medico addMedico(String nombre, String apellido, int matricula) throws SQLException {
        // Validar si la matrícula ya existe
        Optional<Medico> medicoExistente = medicoRepository.findByMatriculaAndEstado(matricula, 1);
        if (medicoExistente.isPresent()) {
            throw new SQLException("La matrícula ya existe.");
        }

        // Crear el nuevo médico
        Medico medico = new Medico();
        medico.setNombre(nombre);
        medico.setApellido(apellido);
        medico.setMatricula(matricula);
        medico.setEstado(1);

        // Guardar en la base de datos
        return save(medico);
    }
}
