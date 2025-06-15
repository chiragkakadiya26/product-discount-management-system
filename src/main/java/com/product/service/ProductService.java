package com.product.service;

        import com.product.payload.DiscountRequest;
        import com.product.payload.DiscountResponse;
        import com.product.payload.ProductDto;
        import com.product.payload.ProductResponse;

public interface ProductService {

    ProductDto CreateProduct(ProductDto productDto);
    ProductDto getProductByProductId(String productId);
    DiscountResponse applyDiscount(DiscountRequest request);
}
