package at.fhv.ecommerce.application.order.command;

import java.util.Map;
import java.util.UUID;
import at.fhv.ecommerce.application.common.command.Command;

public record PlaceOrderCommand(UUID orderId, UUID userId, Map<UUID, Integer> items)
    implements Command {
}
