package at.fhv.ecommerce.user.write.application.command;

import java.util.UUID;

public record RegisterUser(UUID userId, String name) {
}
