package at.fhv.ecommerce.application.product.command;

import java.util.UUID;
import at.fhv.ecommerce.application.common.command.Command;

public record ReduceProductStockCommand(UUID productId, Integer amount) implements Command {
}
