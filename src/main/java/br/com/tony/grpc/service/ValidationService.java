package br.com.tony.grpc.service;

import br.com.tony.grpc.ProductRequest;

public interface ValidationService {
    void validate(ProductRequest request);
}
