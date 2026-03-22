package at.fhv.ecommerce.user.read.projection;

import java.util.List;

public record UserDetail(String userId, String name, List<CartItem> cart) {
}
