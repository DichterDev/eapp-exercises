package at.fhv.order.application.command;

import java.util.UUID;

public record CancelOrder(UUID orderId) {
}
