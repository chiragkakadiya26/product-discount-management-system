package com.product.service.impl;

import com.product.exeption.InsufficientStockException;
import com.product.exeption.InvalidDiscountException;
import com.product.exeption.ResourceNotFoundException;
import com.product.model.Product;
import com.product.payload.DiscountRequest;
import com.product.payload.DiscountResponse;
import com.product.payload.ProductDto;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    @Value("${app.seasonal-discount.percentage}")
    private BigDecimal seasonalDiscountPercentage;

    @Value("${app.seasonal-discount.start-date}")
    private String seasonalStartDate;

    @Value("${app.seasonal-discount.end-date}")
    private String seasonalEndDate;

    @Override
    public ProductDto CreateProduct(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        product.setProductId(UUID.randomUUID().toString());
        Product saveProduct = this.productRepository.save(product);
        return this.modelMapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto getProductByProductId(String productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        boolean seasonalDiscountAvailable = isSeasonalDiscountValid();

        BigDecimal discountedPrice = product.getPrice();
        if (seasonalDiscountAvailable) {
            BigDecimal seasonalDiscount = product.getPrice()
                    .multiply(seasonalDiscountPercentage)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            discountedPrice = product.getPrice().subtract(seasonalDiscount);
        }

        ProductDto dto = modelMapper.map(product, ProductDto.class);
        dto.setOriginalPrice(product.getPrice());
        dto.setDiscountedPrice(discountedPrice);
        dto.setSeasonalDiscountAvailable(seasonalDiscountAvailable);

        return dto;
    }

    @Override
    public DiscountResponse applyDiscount(DiscountRequest request) {
        Product product = productRepository.findByProductId(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + request.getProductId()));

        if (product.getQuantity() < request.getQuantity()) {
            throw new InsufficientStockException("Only " + product.getQuantity() + " units in stock.");
        }

        if (product.getQuantity() <= 0){
            throw new InsufficientStockException("The product is out of stock");
        }

        if(!"flat".equalsIgnoreCase(request.getDiscountType()
                ) && !"percentage".equalsIgnoreCase(request.getDiscountType())){
            throw new InvalidDiscountException("Invalid discount type");
        }

        BigDecimal originalPrice = request.getProductPrice();
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal seasonalDiscountAmount = BigDecimal.ZERO;

        if ("percentage".equalsIgnoreCase(request.getDiscountType())) {
            if (request.getDiscountValue().compareTo(BigDecimal.valueOf(100)) >= 0) {
                throw new InvalidDiscountException("The discount exceeds the product price");
            }
            discountAmount = originalPrice.multiply(request.getDiscountValue())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        } else if ("flat".equalsIgnoreCase(request.getDiscountType())) {
            discountAmount = request.getDiscountValue();
            if (discountAmount.compareTo(originalPrice) >= 0) {
                throw new InvalidDiscountException("The discount exceeds the product price");
            }

        }

        BigDecimal priceAfterPrimaryDiscount = originalPrice.subtract(discountAmount);

        boolean seasonalDiscountApplied = false;
        if (request.getSeasonalDiscountActive() && isSeasonalDiscountValid()) {
            if (priceAfterPrimaryDiscount.compareTo(BigDecimal.ZERO) > 0) {
                seasonalDiscountAmount = priceAfterPrimaryDiscount.multiply(seasonalDiscountPercentage)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            }
            seasonalDiscountApplied = true;
        }

        BigDecimal finalPrice = priceAfterPrimaryDiscount.subtract(seasonalDiscountAmount);

        if (finalPrice.compareTo(BigDecimal.ZERO) < 0) {
            finalPrice = BigDecimal.ZERO;
        }


        DiscountResponse response = new DiscountResponse();
        response.setProductId(request.getProductId());
        response.setOriginalPrice(originalPrice);
        response.setDiscountAmount(discountAmount);
        response.setSeasonalDiscountAmount(seasonalDiscountAmount);
        response.setFinalPrice(finalPrice);
        response.setDiscountType(request.getDiscountType().toLowerCase());
        response.setSeasonalDiscountApplied(seasonalDiscountApplied);
        response.setMessage("Discount applied successfully");
        response.setSuccess(true);
        return response;
    }

    private boolean isSeasonalDiscountValid() {
        try {
            LocalDate today = LocalDate.now();
            LocalDate startDate = LocalDate.parse(seasonalStartDate, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate endDate = LocalDate.parse(seasonalEndDate, DateTimeFormatter.ISO_LOCAL_DATE);

            return !today.isBefore(startDate) && !today.isAfter(endDate);
        } catch (Exception e) {
            return false;
        }
    }
}
