package br.com.tony.grpc.resource;

import br.com.tony.grpc.ProductRequest;
import br.com.tony.grpc.ProductResponse;
import br.com.tony.grpc.ProductsServiceGrpc;
import br.com.tony.grpc.dto.ProductRequestDto;
import br.com.tony.grpc.exception.BusinessException;
import br.com.tony.grpc.service.ProductService;
import br.com.tony.grpc.service.ValidationService;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ProductResource extends ProductsServiceGrpc.ProductsServiceImplBase {

    private final ProductService productService;
    private final ValidationService validationService;

    public ProductResource(ProductService productService, ValidationService validationService) {
        this.productService = productService;
        this.validationService = validationService;
    }

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

        try {
            this.validationService.validate(request);

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
        } catch (BusinessException e) {
            responseObserver.onError(e.getStatus().withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
