package at.fhv.ecommerce.application.order.command;

import java.util.UUID;
import at.fhv.ecommerce.application.common.command.Command;
import at.fhv.ecommerce.domain.order.model.OrderId;

public record FailOrderCommand(UUID orderId) implements Command {
}
