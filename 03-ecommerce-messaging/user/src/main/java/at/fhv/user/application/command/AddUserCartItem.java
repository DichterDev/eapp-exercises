package at.fhv.user.application.command;

import java.util.UUID;

public record AddUserCartItem(
        UUID userId,
        UUID productId,
        Integer amount
) {
}