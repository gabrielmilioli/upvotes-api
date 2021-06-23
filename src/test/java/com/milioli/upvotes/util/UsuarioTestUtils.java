package com.milioli.upvotes.util;

import com.milioli.upvotes.model.entity.usuario.Usuario;

import java.time.LocalDateTime;

public class UsuarioTestUtils {

    public static Long ID = 1L;
    public static String NOME = "Gabriel";
    public static String EMAIL = "gabriel@email.com";
    public static String SENHA = "gabriel123";
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
                .senha(SENHA)
                .dataHoraCriacao(DH_CRIACAO)
                .build();
    }
}
