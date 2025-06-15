package com.product.payload;

import java.math.BigDecimal;

public class ProductResponse {

    private String productId;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private String discountApplied;

    public ProductResponse() {
    }

    public ProductResponse(String productId, BigDecimal originalPrice, BigDecimal discountedPrice, String discountApplied) {
        this.productId = productId;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.discountApplied = discountApplied;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(String discountApplied) {
        this.discountApplied = discountApplied;
    }
}
