package com.milioli.upvotes.exceptions;

public class AutenticacaoException extends RuntimeException {

    public static String ERRO_EMAIL_NAO_ENCONTRADO = "";
    public static String ERRO_AUTENTICACAO_INVALIDA = "";

    public AutenticacaoException(String message) {
        super(message);
    }
}
