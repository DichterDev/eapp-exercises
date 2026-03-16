package at.fhv.ecommerce.application.user.view;

import java.util.List;
import java.util.UUID;

public record UserDetailView(UUID id, String name, List<CartItemView> cart) {
}
