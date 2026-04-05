package com.finance.finance_api.domain.excepcions;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}
