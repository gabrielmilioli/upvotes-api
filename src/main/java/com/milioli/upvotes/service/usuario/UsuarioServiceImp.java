package com.milioli.upvotes.service.usuario;

import com.milioli.upvotes.exceptions.AutenticacaoException;
import com.milioli.upvotes.exceptions.RegraNegocioException;
import com.milioli.upvotes.model.entity.usuario.Usuario;
import com.milioli.upvotes.model.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

import static com.milioli.upvotes.exceptions.AutenticacaoException.ERRO_AUTENTICACAO_INVALIDA;
import static com.milioli.upvotes.exceptions.AutenticacaoException.ERRO_EMAIL_NAO_ENCONTRADO;
import static com.milioli.upvotes.exceptions.RegraNegocioException.ERRO_EMAIL_JA_CADASTRADO;
import static com.milioli.upvotes.model.entity.usuario.Usuario.convertSenha;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario getById(Long id) {
        return repository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> buscar(Usuario usuario) {
        final PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("dataHoraCriacao"));

        Example<Usuario> example = Example.of(usuario,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return repository.findAll(example, pageRequest);
    }

    @Override
    public Usuario autenticar(String email, String senha) {

        final Usuario usuario = repository.findByEmail(email);

        if (Objects.isNull(usuario)) {
            throw new AutenticacaoException(ERRO_EMAIL_NAO_ENCONTRADO);
        }

        if (!usuario.getSenha().equals(convertSenha(senha))) {
            throw new AutenticacaoException(ERRO_AUTENTICACAO_INVALIDA);
        }

        return usuario;
    }

    @Override
    @Transactional
    public Usuario salvar(Usuario usuario) {

        if (Objects.isNull(usuario.getId())) {
            usuario.setSenha(convertSenha(usuario.getSenha()));
        }

        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        if (repository.existsByEmail(email)) {
            throw new RegraNegocioException(ERRO_EMAIL_JA_CADASTRADO);
        }
    }

}
