package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Usuario;
import com.ybc.ybioq.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    public Usuario autenticar(String usuario, String password) {
        return loginService.autenticar(usuario, password);
    }

    public Usuario registrarUsuario(Usuario usuario) {
        return loginService.registrarUsuario(usuario);
    }
}
