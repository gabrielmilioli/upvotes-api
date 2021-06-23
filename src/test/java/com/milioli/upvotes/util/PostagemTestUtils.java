package com.milioli.upvotes.util;

import com.milioli.upvotes.model.entity.postagem.Postagem;
import com.milioli.upvotes.model.entity.usuario.Usuario;

import java.time.LocalDateTime;

public class PostagemTestUtils {

    public static Long ID = 1L;
    public static String TITULO = "Título da postagem";
    public static String CONTEUDO = "Conteúdo da postagem";
    public static Usuario USUARIO = UsuarioTestUtils.constroiUsuarioComId();
    public static LocalDateTime DH_CRIACAO = LocalDateTime.now();

    public static Postagem constroiPostagemSemId() {
        return buildaPostagem()
                .build();
    }

    public static Postagem constroiPostagemComId() {
        return buildaPostagem()
                .id(ID)
                .build();
    }

    private static Postagem.PostagemBuilder buildaPostagem() {
        return Postagem.builder()
                .id(ID)
                .titulo(TITULO)
                .conteudo(CONTEUDO)
                .usuario(USUARIO)
                .dataHoraCriacao(DH_CRIACAO);
    }

}
