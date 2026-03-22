package at.fhv.ecommerce.user.read.projection;

import java.util.List;

public record UserOrder(String orderId, List<OrderItem> items) {
}
