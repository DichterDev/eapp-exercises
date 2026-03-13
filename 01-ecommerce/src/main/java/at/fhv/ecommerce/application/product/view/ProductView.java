package at.fhv.ecommerce.application.product.view;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductView(
    UUID id, String name, String description, BigDecimal price, String currency, Integer stock
) {
}
