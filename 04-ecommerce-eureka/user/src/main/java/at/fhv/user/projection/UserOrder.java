package at.fhv.user.projection;

import java.util.List;
import java.util.UUID;

public record UserOrder(
    UUID orderId,
    List<OrderItem> orderItems,
    String status
) {
}
