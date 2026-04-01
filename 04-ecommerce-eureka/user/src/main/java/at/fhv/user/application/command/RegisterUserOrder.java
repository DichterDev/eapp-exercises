package at.fhv.user.application.command;

import java.util.UUID;

public record RegisterUserOrder(UUID orderId,
                                UUID userId) {
}
