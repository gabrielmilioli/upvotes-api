package com.milioli.upvotes.service.usuario;

import com.milioli.upvotes.model.entity.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvar(Usuario usuario);

    void validarEmail(String email);

    Page<Usuario> buscar(Usuario usuario);

}
