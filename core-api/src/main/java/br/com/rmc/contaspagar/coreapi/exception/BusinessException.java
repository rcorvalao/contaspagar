package br.com.rmc.contaspagar.coreapi.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
