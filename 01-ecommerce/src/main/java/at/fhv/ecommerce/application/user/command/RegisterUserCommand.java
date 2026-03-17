package at.fhv.ecommerce.application.user.command;

import java.util.UUID;
import at.fhv.ecommerce.application.common.command.Command;

public record RegisterUserCommand(UUID userId, String name) implements Command {
}
