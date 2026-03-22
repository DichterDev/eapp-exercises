package at.fhv.ecommerce.product.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.common.domain.Money;
import at.fhv.ecommerce.product.write.domain.model.Product;
import at.fhv.ecommerce.product.write.domain.model.ProductId;

public record ProductChangedPrice(
    UUID id,
    ProductId productId,
    Money from,
    Instant occurredAt
)
    implements ProductEvent {
    public ProductChangedPrice(Product product, Money from) {
        this(
            UUID.randomUUID(),
            product.getId(),
            from,
            Instant.now()
        );
    }
}
