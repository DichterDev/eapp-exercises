package at.fhv.ecommerce.application.order.view;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record OrderDetailsView(UUID id, UUID userId, String status, List<OrderItemView> items) {
}
