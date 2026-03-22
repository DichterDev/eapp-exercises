package at.fhv.ecommerce.product.write.application.command;

import java.util.UUID;

public record IncreaseProductStock(UUID productId, Integer amount) {
}
