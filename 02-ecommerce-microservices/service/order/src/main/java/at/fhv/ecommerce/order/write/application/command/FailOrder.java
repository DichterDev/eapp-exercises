package at.fhv.ecommerce.order.write.application.command;

import java.util.UUID;

public record FailOrder(UUID orderId) {
}
