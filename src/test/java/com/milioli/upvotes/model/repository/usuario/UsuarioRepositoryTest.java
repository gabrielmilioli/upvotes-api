package com.milioli.upvotes.model.repository.usuario;

import com.milioli.upvotes.TestFacility;
import com.milioli.upvotes.model.entity.usuario.Usuario;
import com.milioli.upvotes.util.UsuarioTestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

public class UsuarioRepositoryTest extends TestFacility {

    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveRetornarVerdadeiroQuandoHouverUsuarioCadastradoComEmailInformado() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioSemId();
        entityManager.persist(usuario);

        final Boolean exists = repository.existsByEmail(usuario.getEmail());

        Assertions.assertThat(exists).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmailInformado() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioSemId();

        final Boolean exists = repository.existsByEmail(usuario.getEmail());

        Assertions.assertThat(exists).isFalse();
    }

    @Test
    public void devePersistirUmUsuario() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioSemId();
        final Usuario persisted = repository.save(usuario);
        Assertions.assertThat(persisted.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorId() {
        Usuario usuario = UsuarioTestUtils.constroiUsuarioSemId();
        entityManager.persist(usuario);

        final Usuario result = repository.getById(UsuarioTestUtils.ID);

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        Usuario usuario = UsuarioTestUtils.constroiUsuarioSemId();
        entityManager.persist(usuario);

        final Usuario result = repository.findByEmail(UsuarioTestUtils.EMAIL);

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void deveRetornarNullAoBuscarUmUsuarioPorEmailInexistenteEmBaseDeDados() {
        final Usuario result = repository.findByEmail(UsuarioTestUtils.EMAIL);

        Assertions.assertThat(result).isNull();
    }

    @Test
    public void deveRetornarErroAoTentarPersistirUmUsuarioSemNome() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioSemId();
        usuario.setNome("");
        final Throwable throwable = Assertions.catchThrowable(() ->
                repository.save(usuario));

        Assertions.assertThat(throwable)
                .isInstanceOf(ConstraintViolationException.class);
    }

}
