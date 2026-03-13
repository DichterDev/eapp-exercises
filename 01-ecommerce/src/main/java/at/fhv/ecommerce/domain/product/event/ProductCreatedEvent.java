package at.fhv.ecommerce.domain.product.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.domain.product.model.Product;
import at.fhv.ecommerce.domain.product.model.ProductId;

public record ProductCreatedEvent(
    UUID id,
    ProductId productId,
    String name,
    String description,
    Integer stock,
    BigDecimal price,
    String currency,
    Instant occurredAt
)
    implements ProductEvent {
    public ProductCreatedEvent(Product product) {
        this(
            UUID.randomUUID(),
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getStock(),
            product.getPrice().value(),
            product.getPrice().currency().toString(),
            Instant.now()
        );
    }
}
