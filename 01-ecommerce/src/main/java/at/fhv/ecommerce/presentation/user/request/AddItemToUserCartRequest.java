package at.fhv.ecommerce.presentation.user.request;

import java.util.UUID;

public record AddItemToUserCartRequest(UUID userId, UUID productId, Integer amount) {
}
