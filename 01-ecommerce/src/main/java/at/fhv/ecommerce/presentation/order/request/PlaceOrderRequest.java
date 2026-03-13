package at.fhv.ecommerce.presentation.order.request;

import java.util.Map;

public record PlaceOrderRequest(String userId, Map<String, Integer> items) {
}
