package at.fhv.ecommerce.gateway.presentation.request;

import java.util.UUID;

public record AddToCartRequest(UUID userId, UUID productId, Integer quantity) {
}
