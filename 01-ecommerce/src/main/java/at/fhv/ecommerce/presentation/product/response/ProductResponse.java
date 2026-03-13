package at.fhv.ecommerce.presentation.product.response;

import java.math.BigDecimal;

public record ProductResponse(
    String productId,
    String name,
    String description,
    Integer stock,
    BigDecimal price,
    String currency
) {
}
