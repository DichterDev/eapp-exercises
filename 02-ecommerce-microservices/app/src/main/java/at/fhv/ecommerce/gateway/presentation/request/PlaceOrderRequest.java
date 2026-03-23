package at.fhv.ecommerce.gateway.presentation.request;

import java.util.Map;
import java.util.UUID;

public record PlaceOrderRequest(UUID userId, Map<UUID, Integer> items) {
}
