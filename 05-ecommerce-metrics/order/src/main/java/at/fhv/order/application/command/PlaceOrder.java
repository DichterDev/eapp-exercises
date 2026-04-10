package at.fhv.order.application.command;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record PlaceOrder(
    UUID orderId,
    UUID userId,
    Map<UUID, Integer> orderItems
) {
}

