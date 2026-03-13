package at.fhv.ecommerce.application.order.command;

import java.util.Map;
import at.fhv.ecommerce.application.common.command.Command;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.model.UserId;

public record PlaceOrderCommand(UserId userId, Map<ProductId, Integer> items)
    implements Command {
}
