package at.fhv.ecommerce.user.write.application.command;

import java.util.UUID;

public record CheckoutUserCart(UUID userId, UUID orderId) {
}
