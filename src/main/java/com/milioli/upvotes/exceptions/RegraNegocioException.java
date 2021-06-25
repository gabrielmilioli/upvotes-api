package com.milioli.upvotes.exceptions;

public class RegraNegocioException extends RuntimeException {

    public static String ERRO_USUARIO_NAO_ENCONTRADO = "";
    public static String ERRO_EMAIL_JA_CADASTRADO = "";

    public RegraNegocioException(String message) {
        super(message);
    }
}
