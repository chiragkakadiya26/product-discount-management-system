package com.product.payload;

import java.math.BigDecimal;

public class DiscountResponse {

    private String productId;
    private BigDecimal originalPrice;
    private BigDecimal discountAmount;
    private BigDecimal seasonalDiscountAmount;
    private BigDecimal finalPrice;
    private String discountType;
    private Boolean seasonalDiscountApplied;
    private String message;
    private Boolean success;

    public DiscountResponse() {
    }

    public DiscountResponse(String productId, BigDecimal originalPrice, BigDecimal discountAmount, BigDecimal seasonalDiscountAmount, BigDecimal finalPrice, String discountType, Boolean seasonalDiscountApplied, String message, Boolean success) {
        this.productId = productId;
        this.originalPrice = originalPrice;
        this.discountAmount = discountAmount;
        this.seasonalDiscountAmount = seasonalDiscountAmount;
        this.finalPrice = finalPrice;
        this.discountType = discountType;
        this.seasonalDiscountApplied = seasonalDiscountApplied;
        this.message = message;
        this.success = success;
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

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getSeasonalDiscountAmount() {
        return seasonalDiscountAmount;
    }

    public void setSeasonalDiscountAmount(BigDecimal seasonalDiscountAmount) {
        this.seasonalDiscountAmount = seasonalDiscountAmount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Boolean getSeasonalDiscountApplied() {
        return seasonalDiscountApplied;
    }

    public void setSeasonalDiscountApplied(Boolean seasonalDiscountApplied) {
        this.seasonalDiscountApplied = seasonalDiscountApplied;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
