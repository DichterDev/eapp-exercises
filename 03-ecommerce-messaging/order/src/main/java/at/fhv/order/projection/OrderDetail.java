package at.fhv.order.projection;

import java.util.List;
import java.util.UUID;

public record OrderDetail(UUID orderId, UUID userId, List<OrderItem> items, Double orderTotal, String status) {
}
