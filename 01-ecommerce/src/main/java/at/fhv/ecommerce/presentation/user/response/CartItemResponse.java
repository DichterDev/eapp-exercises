package at.fhv.ecommerce.presentation.user.response;

import java.util.UUID;

public record CartItemResponse(UUID productId, Integer amount) {
}
