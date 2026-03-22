package at.fhv.ecommerce.product.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.product.write.domain.model.Product;
import at.fhv.ecommerce.product.write.domain.model.ProductId;

public record ProductCreated(
    UUID id,
    ProductId productId,
    Instant occurredAt
)
    implements ProductEvent {
    public ProductCreated(Product product) {
        this(
            UUID.randomUUID(),
            product.getId(),
            Instant.now()
        );
    }
}
