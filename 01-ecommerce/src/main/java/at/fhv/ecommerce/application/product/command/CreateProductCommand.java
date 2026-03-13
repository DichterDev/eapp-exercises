package at.fhv.ecommerce.application.product.command;

import at.fhv.ecommerce.application.common.command.Command;
import at.fhv.ecommerce.domain.common.Money;

public record CreateProductCommand(String name, String description, Money price, Integer stock)
    implements Command {
}
