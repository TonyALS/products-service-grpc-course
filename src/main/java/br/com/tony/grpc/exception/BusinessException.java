package br.com.tony.grpc.exception;

import io.grpc.Status;

public abstract class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
    public abstract Status getStatus();
}
