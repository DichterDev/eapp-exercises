package at.fhv.product.application.command;

import java.util.UUID;

public record ReduceProductStock(UUID productId, Integer amount, UUID orderId) {
}
