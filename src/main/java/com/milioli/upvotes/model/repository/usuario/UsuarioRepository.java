package com.milioli.upvotes.model.repository.usuario;

import com.milioli.upvotes.model.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByEmail(String email);

    Usuario findByEmail(String email);

}
