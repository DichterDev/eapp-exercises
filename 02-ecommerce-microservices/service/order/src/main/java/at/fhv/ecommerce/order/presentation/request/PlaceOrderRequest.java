package at.fhv.ecommerce.order.presentation.request;

import java.util.Map;

public record PlaceOrderRequest(String orderId, String userId, Map<String, Integer> items) {
}
