package at.fhv.ecommerce.product.write.application.command;

import java.util.UUID;

public record ChangeProductPrice(UUID productId, Double newPrice) {
}
