package at.fhv.ecommerce.domain.product.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.domain.common.DomainEvent;
import at.fhv.ecommerce.domain.product.model.Product;
import at.fhv.ecommerce.domain.product.model.ProductId;

public record ProductIncreasedStockEvent(
    UUID id,
    ProductId productId,
    Integer by,
    Integer to,
    Instant occurredAt
) implements DomainEvent {
    public ProductIncreasedStockEvent(Product product, Integer by) {
        this(
            UUID.randomUUID(),
            product.getId(),
            by,
            product.getStock(),
            Instant.now()
        );
    }
}
