package com.milioli.upvotes.service.usuario;

import com.milioli.upvotes.TestFacility;
import com.milioli.upvotes.exceptions.AutenticacaoException;
import com.milioli.upvotes.exceptions.RegraNegocioException;
import com.milioli.upvotes.model.entity.usuario.Usuario;
import com.milioli.upvotes.model.repository.usuario.UsuarioRepository;
import com.milioli.upvotes.util.UsuarioTestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.milioli.upvotes.exceptions.AutenticacaoException.ERRO_AUTENTICACAO_INVALIDA;
import static org.mockito.ArgumentMatchers.*;

public class UsuarioServiceTest extends TestFacility {

    @SpyBean
    UsuarioServiceImp service;

    @MockBean
    UsuarioRepository repository;

    @Test
    public void deveValidarEmail() {
        Mockito.when(repository.existsByEmail(anyString()))
                .thenReturn(Boolean.FALSE);
        service.validarEmail(UsuarioTestUtils.EMAIL);
    }

    @Test
    public void deveRetornarErroAoValidarEmailJaExistente() {
        Mockito.when(repository.existsByEmail(anyString()))
                .thenReturn(Boolean.TRUE);

        final Throwable throwable = Assertions.catchThrowable(() ->
                service.validarEmail(UsuarioTestUtils.EMAIL));

        Assertions.assertThat(throwable)
                .isInstanceOf(RegraNegocioException.class)
                .hasMessage(RegraNegocioException.ERRO_EMAIL_JA_CADASTRADO);
    }

    @Test
    public void deveRetornarPagina() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioComId();

        Page<Usuario> page = Mockito.mock(Page.class);

        Mockito.when(repository.findAll(any(Example.class), any(Pageable.class)))
                .thenReturn(page);

        service.buscar(usuario);
    }

    @Test
    public void deveObterPorId() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioComId();

        Mockito.when(repository.getById(anyLong())).thenReturn(usuario);

        final Usuario byId = service.getById(UsuarioTestUtils.ID);

        Assertions.assertThat(byId).isNotNull();
    }

    @Test
    public void deveAutenticarUsuario() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioComId();

        Mockito.when(repository.findByEmail(anyString())).thenReturn(usuario);

        final Usuario autenticar = service.autenticar(UsuarioTestUtils.EMAIL, UsuarioTestUtils.SENHA);

        Assertions.assertThat(autenticar).isNotNull();
    }

    @Test
    public void deveRetornarErroAoTentarAutenticarUsuarioComSenhaInvalida() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioComId();

        Mockito.when(repository.findByEmail(anyString())).thenReturn(usuario);

        final Throwable throwable = Assertions.catchThrowable(() ->
                service.autenticar(UsuarioTestUtils.EMAIL, "senha_invalida"));

        Assertions.assertThat(throwable)
                .isInstanceOf(AutenticacaoException.class)
                .hasMessage(ERRO_AUTENTICACAO_INVALIDA);
    }

    @Test
    public void deveSalvarSenhaCriptografada() {
        final Usuario usuario = UsuarioTestUtils.constroiUsuarioSemId();
        final Usuario usuarioSalvo = UsuarioTestUtils.constroiUsuarioComId();

        Mockito.when(repository.save(usuario)).thenReturn(usuarioSalvo);

        final Usuario salvar = service.salvar(usuario);

        Assertions.assertThat(salvar.getSenha()).isEqualTo(usuarioSalvo.getSenha());
    }

}
