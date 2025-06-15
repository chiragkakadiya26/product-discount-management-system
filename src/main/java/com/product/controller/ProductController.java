package com.product.controller;

import com.product.payload.DiscountRequest;
import com.product.payload.DiscountResponse;
import com.product.payload.ProductDto;
import com.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto createProductDto = this.productService.CreateProduct(productDto);
        return new ResponseEntity<>(createProductDto, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
        return ResponseEntity.ok(productService.getProductByProductId(productId));
    }

    @PostMapping("/discount")
    public ResponseEntity<DiscountResponse> applyDiscount(@Valid @RequestBody DiscountRequest discountRequest) {
        return ResponseEntity.ok(productService.applyDiscount(discountRequest));
    }
}
