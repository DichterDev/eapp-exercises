package at.fhv.user.projection;

import java.util.UUID;

public record CartItem(UUID productId, Integer amount) {
}