package at.fhv.ecommerce.application.order.command;

import java.util.UUID;
import at.fhv.ecommerce.application.common.command.Command;

public record CancelOrderCommand(UUID orderId) implements Command {
}
