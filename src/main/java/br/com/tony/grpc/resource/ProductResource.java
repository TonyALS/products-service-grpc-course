package br.com.tony.grpc.resource;

import br.com.tony.grpc.ProductRequest;
import br.com.tony.grpc.ProductResponse;
import br.com.tony.grpc.ProductsServiceGrpc;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ProductResource extends ProductsServiceGrpc.ProductsServiceImplBase {

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

        responseObserver.onNext(ProductResponse.newBuilder()
                .setId(Int64Value.of(1L))
                .setName(request.getName())
                .setPrice(request.getPrice())
                .build());

        responseObserver.onCompleted();
    }
}
