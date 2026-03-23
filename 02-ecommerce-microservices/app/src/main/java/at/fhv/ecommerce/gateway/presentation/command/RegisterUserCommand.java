package at.fhv.ecommerce.gateway.presentation.command;

import java.util.UUID;

public record RegisterUserCommand(UUID userId, String name) {
}
