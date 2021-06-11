package br.com.tony.grpc.service.impl;

import br.com.tony.grpc.domain.Product;
import br.com.tony.grpc.dto.ProductRequestDto;
import br.com.tony.grpc.dto.ProductResponseDto;
import br.com.tony.grpc.repository.ProductRepository;
import br.com.tony.grpc.service.ProductService;

import javax.inject.Singleton;

@Singleton
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto requestDto) {
        var product = new Product(requestDto.name(), requestDto.price());
        return ProductResponseDto.fromEntityToResponseDto(this.repository.save(product));
    }
}
