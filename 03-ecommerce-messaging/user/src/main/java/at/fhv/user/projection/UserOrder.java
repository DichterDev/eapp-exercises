package at.fhv.user.projection;

import java.util.UUID;

public record UserOrder(
        UUID orderId,
        UUID userId,
        String status
) {
}