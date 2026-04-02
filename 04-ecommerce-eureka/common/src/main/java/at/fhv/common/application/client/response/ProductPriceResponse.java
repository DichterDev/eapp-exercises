package at.fhv.common.application.client.response;

import java.util.UUID;

public record ProductPriceResponse(UUID productId, Double price) {
}
