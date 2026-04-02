package at.fhv.common.application.client.response;

import java.util.UUID;

public record OrderItemResponse(UUID productId, Integer amount, Double price) {
}
