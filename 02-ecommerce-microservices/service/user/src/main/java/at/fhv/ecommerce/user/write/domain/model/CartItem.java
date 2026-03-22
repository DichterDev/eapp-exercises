package at.fhv.ecommerce.user.write.domain.model;

import at.fhv.ecommerce.domain.product.model.ProductId;

public record CartItem(ProductId productId, Integer amount) {
}
