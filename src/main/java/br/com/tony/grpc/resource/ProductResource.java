package br.com.tony.grpc.resource;

import br.com.tony.grpc.ProductRequest;
import br.com.tony.grpc.ProductResponse;
import br.com.tony.grpc.ProductsServiceGrpc;
import br.com.tony.grpc.dto.ProductRequestDto;
import br.com.tony.grpc.service.ProductService;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ProductResource extends ProductsServiceGrpc.ProductsServiceImplBase {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

        var productCreated = this.productService
                .create(new ProductRequestDto(
                        request.getName().getValue(),
                        request.getPrice().getValue()));
        
        responseObserver.onNext(ProductResponse.newBuilder()
                .setId(Int64Value.of(productCreated.id()))
                .setName(StringValue.of(productCreated.name()))
                .setPrice(DoubleValue.of(productCreated.price()))
                .build());

        responseObserver.onCompleted();
    }
}
