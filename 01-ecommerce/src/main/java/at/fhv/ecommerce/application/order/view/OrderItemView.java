package at.fhv.ecommerce.application.order.view;

import java.util.UUID;

public record OrderItemView(UUID productId, Integer amount) {
}
