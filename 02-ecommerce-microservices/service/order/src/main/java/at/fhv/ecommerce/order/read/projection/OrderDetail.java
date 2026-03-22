package at.fhv.ecommerce.order.read.projection;

import java.util.List;

public record OrderDetail(
    String orderId,
    String userId,
    String status,
    List<OrderItem> orderItems
) {
}
