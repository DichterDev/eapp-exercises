package at.fhv.ecommerce.application.product.command;

import java.util.UUID;
import at.fhv.ecommerce.application.common.command.Command;
import at.fhv.ecommerce.domain.common.Money;

public record UpdateProductPriceCommand(UUID productId, Money newPrice) implements Command {
}
