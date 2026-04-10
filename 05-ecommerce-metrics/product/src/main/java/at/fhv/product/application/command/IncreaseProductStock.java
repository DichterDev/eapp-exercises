package at.fhv.product.application.command;

import java.util.UUID;

public record IncreaseProductStock(UUID productId, Integer amount) {
}
