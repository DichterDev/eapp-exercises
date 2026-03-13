package at.fhv.ecommerce.application.order.command;

import at.fhv.ecommerce.application.common.command.Command;
import at.fhv.ecommerce.domain.order.model.OrderId;

public record CompleteOrderCommand(OrderId orderId) implements Command {
}
