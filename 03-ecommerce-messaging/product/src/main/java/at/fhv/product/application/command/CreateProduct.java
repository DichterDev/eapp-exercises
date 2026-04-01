package at.fhv.product.application.command;

import java.util.UUID;

public record CreateProduct(UUID productId, String name, String description, Double price, Integer stock) {
}
