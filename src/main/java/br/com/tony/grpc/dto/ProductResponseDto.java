package br.com.tony.grpc.dto;

import br.com.tony.grpc.domain.Product;

public record ProductResponseDto(Long id, String name, Double price) {
    public static ProductResponseDto fromEntityToResponseDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice());
    }
}
