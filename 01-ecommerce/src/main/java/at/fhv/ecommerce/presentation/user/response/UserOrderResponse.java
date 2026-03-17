package at.fhv.ecommerce.presentation.user.response;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import at.fhv.ecommerce.presentation.order.response.OrderItemResponse;

public record UserOrderResponse(UUID orderId, List<OrderItemResponse> items) {
}
