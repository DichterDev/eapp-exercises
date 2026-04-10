package at.fhv.common.application.client.response;

import java.util.List;
import java.util.UUID;

public record OrderResponse(UUID orderId, List<OrderItemResponse> orderItems, String status) {
}
