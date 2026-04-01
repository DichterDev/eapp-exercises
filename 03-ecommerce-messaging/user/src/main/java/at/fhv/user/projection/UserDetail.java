package at.fhv.user.projection;

import java.util.List;
import java.util.UUID;

public record UserDetail(UUID userId, String name, List<CartItem> cart) {
}