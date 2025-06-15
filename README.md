# Product Discount Management System

A Spring Boot application for managing product discounts in an e-commerce platform. The system supports flat discounts, percentage discounts, and seasonal discounts with comprehensive business logic validation.

## Features

- **Flat Discount**: Fixed amount subtracted from product price
- **Percentage Discount**: Percentage-based discount calculation
- **Seasonal Discount**: Additional 25% discount during specified seasons
- **Stock Validation**: Discounts only apply to in-stock products
- **Price Protection**: Prevents negative final prices
- **PostgreSQL Integration**: Persistent data storage
- **Comprehensive API**: RESTful endpoints for all operations

## Technologies Used

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- Maven
- Jakarta Validation

## Database Setup

1. Install PostgreSQL and create a database:
```sql
CREATE DATABASE discount_management;
```

2. Update `application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/discount_management
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Installation & Running

1. Clone the repository:
```bash
git clone <repository-url>
cd product-discount-management-system
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on http://localhost:8080

## API Endpoints

### 1. Apply Discount
**POST** `api/product/discount`

Apply discount to a product based on specified rules.

**Request Body:**
```json
{
  "productId": "7d1cc744-8a0c-4b01-9cbf-8a760841eee9",
  "discountType": "percentage",
  "discountValue": "10",
  "seasonalDiscountActive": true,
  "productPrice": 299.99,
  "quantity": 5
}
```

**Response:**
```json
{
  "productId": "7d1cc744-8a0c-4b01-9cbf-8a760841eee9",
  "originalPrice": 100.00,
  "discountAmount": 10.00,
  "seasonalDiscountAmount": 22.50,
  "finalPrice": 67.50,
  "discountType": "percentage",
  "seasonalDiscountApplied": true,
  "message": "Discount applied successfully",
  "success": true
}
```

### 2. Get Product Details
**GET** `api/product/{productId}`

Retrieve product details including discounted price if applicable.

**Response:**
```json
{
  "productId": "7d1cc744-8a0c-4b01-9cbf-8a760841eee9",
  "name": "OnePlus Nord",
  "description": "Fast and affordable smartphone",
  "originalPrice": 299.99,
  "discountedPrice": 239.99,
  "quantity": 15,
  "inStock": true,
  "seasonalDiscountAvailable": true,
  "createdAt": "2025-06-14T21:29:17.379331",
  "updatedAt": "2025-06-14T21:29:17.379331"
}
```

## API Testing Examples

### Using cURL

#### 1. Apply Percentage Discount
```bash
curl -X POST http://localhost:8080/api/product/discount \
  -H "Content-Type: application/json" \
  -d '{
      "productId": "7d1cc744-8a0c-4b01-9cbf-8a760841eee9",
      "discountType": "percentage",
      "discountValue": "10",
      "seasonalDiscountActive": true,
      "productPrice": 299.99,
      "quantity": 5
}'
```

#### 2. Apply Flat Discount
```bash
curl -X POST http://localhost:8080/api/product/discount \
  -H "Content-Type: application/json" \
  -d '{
  "productId": "7d1cc744-8a0c-4b01-9cbf-8a760841eee9",
  "discountType": "flat",
  "discountValue": "10",
  "seasonalDiscountActive": true,
  "productPrice": 299.99,
  "quantity": 5
}'
```

#### 3. Get Product Details
```bash
curl -X GET http:/localhost:8080/api/product/7d1cc744-8a0c-4b01-9cbf-8a760841eee9
```

#### 4. Test Out of Stock Product
```bash
curl -X POST http://localhost:8080/product/discount \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "7d1cc744-8a0c-4b01-9cbf-8a760841eee9",
    "discountType": "percentage",
    "discountValue": 10,
    "seasonalDiscountActive": true,
    "productPrice": 50,
    "quantity": 0
  }'
```

## Business Logic Rules

- **Stock Validation**: Discounts only apply if product quantity > 0
- **Discount Types**: Supports "flat" and "percentage" discount types
- **Price Protection**: Final price never goes below $0.00
- **Seasonal Discount**: Additional 25% discount during configured season
- **Validation**: Comprehensive input validation with error messages

## Seasonal Discount Configuration

Configure seasonal discount period in `application.properties`:

```properties
app.seasonal-discount.percentage=25.0
app.seasonal-discount.start-date=2024-11-01
app.seasonal-discount.end-date=2024-12-31
```

## Error Handling

The application provides comprehensive error handling:

- **400 Bad Request**: Validation errors, invalid input
- **404 Not Found**: Product not found
- **500 Internal Server Error**: Server errors