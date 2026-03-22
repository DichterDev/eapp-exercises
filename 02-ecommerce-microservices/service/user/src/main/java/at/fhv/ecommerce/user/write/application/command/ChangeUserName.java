package at.fhv.ecommerce.user.write.application.command;

import java.util.UUID;

public record ChangeUserName(UUID userId, String newName) {
}
