package br.com.tony.grpc.service.impl;

import br.com.tony.grpc.ProductRequest;
import br.com.tony.grpc.exception.InvalidArgumentException;
import br.com.tony.grpc.service.ValidationService;
import com.google.protobuf.DoubleValue;

import javax.inject.Singleton;

@Singleton
public class ValidationServiceImpl implements ValidationService {

    private static final String PRICE_IS_REQUIRED = "Price is required";
    private static final String NAME_IS_REQUIRED = "Name is required";

    @Override
    public void validate(ProductRequest request) {
        if (request.getPrice().equals(DoubleValue.getDefaultInstance())) {
            throw new InvalidArgumentException(PRICE_IS_REQUIRED);
        }

        if (Double.isNaN(request.getPrice().getValue())) {
            throw new InvalidArgumentException(PRICE_IS_REQUIRED);
        }

        if (!request.hasName() || request.getName().getValue().isBlank()) {
            throw new InvalidArgumentException(NAME_IS_REQUIRED);
        }
    }
}
