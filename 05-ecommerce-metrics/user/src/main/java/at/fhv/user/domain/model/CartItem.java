package at.fhv.user.domain.model;

import at.fhv.common.domain.model.Money;

public record CartItem(ProductId productId, Integer amount) {
}

