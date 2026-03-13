package at.fhv.ecommerce.application.order.view;

import java.util.UUID;

public record OrderView(UUID id, UUID userId, String status) {
}
