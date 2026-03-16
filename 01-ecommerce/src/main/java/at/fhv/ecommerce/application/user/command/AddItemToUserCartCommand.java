package at.fhv.ecommerce.application.user.command;

import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.model.UserId;

public record AddItemToUserCartCommand(UserId userId, ProductId productId, Integer amount) {
}
