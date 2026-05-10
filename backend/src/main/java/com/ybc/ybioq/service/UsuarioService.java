package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Usuario;
import com.ybc.ybioq.repository.local.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService extends AbstractCrudService<Usuario, Integer> {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Optional<Usuario> findByUsuario(String usuario) {
        return repository.findByUsuario(usuario);
    }
}
