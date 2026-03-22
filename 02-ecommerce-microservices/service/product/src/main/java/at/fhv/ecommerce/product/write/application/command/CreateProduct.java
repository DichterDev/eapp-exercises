package at.fhv.ecommerce.product.write.application.command;

import java.util.UUID;

public record CreateProduct(UUID id, String name, String description, Double price, Integer stock) {
}
