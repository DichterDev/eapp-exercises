package at.fhv.ecommerce.presentation.order.response;

import java.util.List;

public record OrderDetailResponse(
    String orderId, String userId, String status, List<OrderItemResponse> orderItems
) {
}
