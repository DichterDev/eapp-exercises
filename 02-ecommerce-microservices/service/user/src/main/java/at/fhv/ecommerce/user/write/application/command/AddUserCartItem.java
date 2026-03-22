package at.fhv.ecommerce.user.write.application.command;

import java.util.UUID;

public record AddUserCartItem(UUID userId, UUID productId, Integer amount) {
}
