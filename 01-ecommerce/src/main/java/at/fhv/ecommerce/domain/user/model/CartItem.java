package at.fhv.ecommerce.domain.user.model;

import at.fhv.ecommerce.domain.product.model.ProductId;

public record CartItem(ProductId productId, Integer amount) {
}
