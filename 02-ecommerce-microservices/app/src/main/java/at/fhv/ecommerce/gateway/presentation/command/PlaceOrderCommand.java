package at.fhv.ecommerce.gateway.presentation.command;

import java.util.Map;
import java.util.UUID;

public record PlaceOrderCommand(UUID orderId, UUID userId, Map<UUID, Integer> items) {
}
