package at.fhv.order.application.command;

import java.util.UUID;

public record CompleteOrder(UUID orderId) {
}
