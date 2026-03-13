package at.fhv.ecommerce.application.user.command;

import at.fhv.ecommerce.application.common.command.Command;

public record RegisterUserCommand(String name) implements Command {
}
