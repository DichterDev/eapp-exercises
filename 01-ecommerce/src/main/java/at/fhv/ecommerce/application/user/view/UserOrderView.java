package at.fhv.ecommerce.application.user.view;

import java.util.List;
import java.util.UUID;
import at.fhv.ecommerce.application.order.view.OrderItemView;

public record UserOrderView(UUID orderId, List<OrderItemView> items) {
}
