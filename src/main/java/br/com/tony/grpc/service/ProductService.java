package br.com.tony.grpc.service;

import br.com.tony.grpc.dto.ProductRequestDto;
import br.com.tony.grpc.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto create(ProductRequestDto requestDto);
}
