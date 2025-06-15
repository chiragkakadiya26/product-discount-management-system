package com.product.payload;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDto {

    private String productId;
    private String name;
    private String description;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private Integer quantity;
    private Boolean seasonalDiscountAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ProductDto() {
    }

    public ProductDto(String productId, String name, String description, BigDecimal originalPrice, BigDecimal discountedPrice, Integer quantity, Boolean seasonalDiscountAvailable, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.quantity = quantity;
        this.seasonalDiscountAvailable = seasonalDiscountAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getSeasonalDiscountAvailable() {
        return seasonalDiscountAvailable;
    }

    public void setSeasonalDiscountAvailable(Boolean seasonalDiscountAvailable) {
        this.seasonalDiscountAvailable = seasonalDiscountAvailable;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
