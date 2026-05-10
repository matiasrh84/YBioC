package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.LoginRequest;
import com.ybc.ybioq.api.dto.UsuarioResponse;
import com.ybc.ybioq.entity.local.Usuario;
import com.ybc.ybioq.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final LoginService loginService;

    public AuthApiController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody LoginRequest request) {
        String usuario = request.usuario() == null ? "" : request.usuario().trim();
        String clave = request.clave() == null ? "" : request.clave();
        if (usuario.isBlank() || clave.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese usuario y clave.");
        }

        try {
            Usuario autenticado = loginService.autenticar(usuario, clave);
            return UsuarioResponse.from(autenticado);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
