package at.fhv.ecommerce.application.user.view;

import java.util.UUID;

public record CartItemView(UUID productId, Integer amount) {
}
