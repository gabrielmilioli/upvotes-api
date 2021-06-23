package com.milioli.upvotes.model.repository.postagem;

import com.milioli.upvotes.model.entity.postagem.Postagem;
import com.milioli.upvotes.model.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    Postagem findByUsuario(Usuario usuario);

}
