package at.fhv.order.application.command;

import java.util.UUID;

public record FailOrder(UUID orderId) {
}
