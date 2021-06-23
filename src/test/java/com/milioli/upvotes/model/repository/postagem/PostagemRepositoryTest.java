package com.milioli.upvotes.model.repository.postagem;

import com.milioli.upvotes.TestFacility;
import com.milioli.upvotes.model.entity.postagem.Postagem;
import com.milioli.upvotes.model.entity.usuario.Usuario;
import com.milioli.upvotes.model.repository.usuario.UsuarioRepository;
import com.milioli.upvotes.util.PostagemTestUtils;
import com.milioli.upvotes.util.UsuarioTestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PostagemRepositoryTest extends TestFacility {

    @Autowired
    PostagemRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private void persisteUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Test
    public void devePersistirUmaPostagem() {
        final Postagem postagem = PostagemTestUtils.constroiPostagemSemId();

        persisteUsuario(postagem.getUsuario());

        final Postagem saved = repository.save(postagem);

        Assertions.assertThat(saved.getId()).isNotNull();
    }

}
