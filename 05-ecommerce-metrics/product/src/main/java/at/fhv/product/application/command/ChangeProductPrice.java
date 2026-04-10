package at.fhv.product.application.command;

import java.util.UUID;

public record ChangeProductPrice(UUID productId, Double price) {
}
