package at.fhv.order.projection;

import java.util.UUID;

public record OrderItem(UUID productId, Integer amount, Double unitPrice, Double lineTotal) {
}