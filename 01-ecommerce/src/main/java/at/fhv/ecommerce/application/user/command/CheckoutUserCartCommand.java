package at.fhv.ecommerce.application.user.command;

import at.fhv.ecommerce.application.common.command.Command;
import at.fhv.ecommerce.domain.user.model.UserId;

public record CheckoutUserCartCommand(UserId userId) implements Command {
}
