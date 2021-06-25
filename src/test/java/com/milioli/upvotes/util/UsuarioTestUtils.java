package com.milioli.upvotes.util;

import com.milioli.upvotes.model.entity.usuario.Usuario;

import java.time.LocalDateTime;

import static com.milioli.upvotes.model.entity.usuario.Usuario.convertSenha;

public class UsuarioTestUtils {

    public static Long ID = 1L;
    public static String NOME = "Gabriel";
    public static String EMAIL = "gabriel@email.com";
    public static String SENHA = "gabriel123";
    public static String SENHA_CONVERTIDA = convertSenha(SENHA);
    public static LocalDateTime DH_CRIACAO = LocalDateTime.now();

    public static Usuario constroiUsuarioSemId() {
        return Usuario.builder()
                .nome(NOME)
                .email(EMAIL)
                .senha(SENHA)
                .dataHoraCriacao(DH_CRIACAO)
                .build();
    }

    public static Usuario constroiUsuarioComId() {
        return Usuario.builder()
                .id(ID)
                .nome(NOME)
                .email(EMAIL)
                .senha(SENHA_CONVERTIDA)
                .dataHoraCriacao(DH_CRIACAO)
                .build();
    }
}
