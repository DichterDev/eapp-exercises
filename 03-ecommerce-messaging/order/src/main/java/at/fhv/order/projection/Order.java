package at.fhv.order.projection;

import java.util.UUID;

public record Order(UUID orderId, String status) {
}
