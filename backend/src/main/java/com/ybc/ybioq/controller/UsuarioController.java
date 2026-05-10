package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Usuario;
import com.ybc.ybioq.service.UsuarioService;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UsuarioController extends AbstractCrudController<Usuario, Integer> {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
        this.usuarioService = usuarioService;
    }

    public Optional<Usuario> findByUsuario(String usuario) {
        return usuarioService.findByUsuario(usuario);
    }
}
