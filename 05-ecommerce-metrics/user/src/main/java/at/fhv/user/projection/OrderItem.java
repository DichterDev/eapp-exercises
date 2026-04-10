package at.fhv.user.projection;

import java.util.UUID;

public record OrderItem(UUID productId, Integer amount, Double price) {
}

