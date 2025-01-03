package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Usuario;
import com.ybc.ybioq.exception.LoginException;
import com.ybc.ybioq.repository.local.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario autenticar(String usuario, String clave) throws LoginException {

        Usuario usuarioEncontrado = usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Nombre de usuario incorrecto"));
        if (!passwordEncoder.matches(clave, usuarioEncontrado.getClave())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return usuarioEncontrado;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        String claveHash = passwordEncoder.encode(usuario.getClave());
        usuario.setClave(claveHash);
        return usuarioRepository.save(usuario);
    }
}