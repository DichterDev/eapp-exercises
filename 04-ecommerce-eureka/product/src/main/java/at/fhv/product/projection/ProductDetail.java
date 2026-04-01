package at.fhv.product.projection;

import at.fhv.common.domain.model.Money;

import java.util.UUID;

public record ProductDetail(UUID productId, String name, String description, Double price, String currency, Integer stock) {
}
