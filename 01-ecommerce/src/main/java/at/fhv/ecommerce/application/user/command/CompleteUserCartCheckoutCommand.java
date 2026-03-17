package at.fhv.ecommerce.application.user.command;

import at.fhv.ecommerce.application.common.command.Command;
import at.fhv.ecommerce.domain.user.model.UserId;

public record CompleteUserCartCheckoutCommand(UserId userId) implements Command {
}
