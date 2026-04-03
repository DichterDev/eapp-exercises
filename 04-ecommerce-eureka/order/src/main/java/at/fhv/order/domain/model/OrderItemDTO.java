package at.fhv.order.domain.model;

import java.util.UUID;
import at.fhv.common.domain.model.Money;

public record OrderItemDTO(ProductId productId, Integer amount, Money price) {
}
