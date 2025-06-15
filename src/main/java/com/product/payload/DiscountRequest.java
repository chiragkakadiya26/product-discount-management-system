package com.product.payload;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class DiscountRequest {

    @NotBlank(message = "Product ID is required")
    private String productId;
    @NotBlank(message = "Discount type is required")
    private String discountType;
    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount value must be greater than 0")
    private BigDecimal discountValue;
    @NotNull(message = "Seasonal discount active flag is required")
    private boolean seasonalDiscountActive;
    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than 0")
    private BigDecimal productPrice;
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    public DiscountRequest() {
    }

    public DiscountRequest(String productId, String discountType, BigDecimal discountValue, boolean seasonalDiscountActive, BigDecimal productPrice, int quantity) {
        this.productId = productId;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.seasonalDiscountActive = seasonalDiscountActive;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal  getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal  discountValue) {
        this.discountValue = discountValue;
    }

    public Boolean getSeasonalDiscountActive() {
        return seasonalDiscountActive;
    }

    public void setSeasonalDiscountActive(boolean seasonalDiscountActive) {
        this.seasonalDiscountActive = seasonalDiscountActive;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
