package at.fhv.ecommerce.product.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.product.write.domain.model.Product;
import at.fhv.ecommerce.product.write.domain.model.ProductId;

public record ProductReducedStock(
    UUID id,
    ProductId productId,
    Integer by,
    Instant occurredAt
)
    implements ProductEvent {
    public ProductReducedStock(Product product, Integer by) {
        this(
            UUID.randomUUID(),
            product.getId(),
            by,
            Instant.now()
        );
    }
}
