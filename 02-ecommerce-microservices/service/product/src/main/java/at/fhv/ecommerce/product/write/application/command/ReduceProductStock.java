package at.fhv.ecommerce.product.write.application.command;

import java.util.UUID;

public record ReduceProductStock(UUID productId, Integer amount) {
}
