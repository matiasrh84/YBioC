package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
