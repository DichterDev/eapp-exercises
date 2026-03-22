package at.fhv.ecommerce.product.write.infrastructure.messaging;

import java.util.UUID;

public record ProductIntegration(UUID eventId, UUID productId, String action) {
}
