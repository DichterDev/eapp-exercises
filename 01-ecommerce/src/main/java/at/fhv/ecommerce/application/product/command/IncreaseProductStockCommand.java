package at.fhv.ecommerce.application.product.command;

import at.fhv.ecommerce.application.common.command.Command;
import at.fhv.ecommerce.domain.product.model.ProductId;

public record IncreaseProductStockCommand(ProductId productId, Integer amount) implements Command {
}
