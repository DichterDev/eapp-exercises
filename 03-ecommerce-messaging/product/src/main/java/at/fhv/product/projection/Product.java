package at.fhv.product.projection;

import java.util.UUID;

public record Product(UUID productId, String name, Double price, String currency) {
}
