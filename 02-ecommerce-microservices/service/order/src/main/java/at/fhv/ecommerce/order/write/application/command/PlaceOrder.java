package at.fhv.ecommerce.order.write.application.command;

import java.util.Map;
import java.util.UUID;

public record PlaceOrder(UUID orderId, UUID userId, Map<UUID, Integer> orderItems) {
}
