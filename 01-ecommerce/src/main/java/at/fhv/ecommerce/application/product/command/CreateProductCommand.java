package at.fhv.ecommerce.application.product.command;

import java.math.BigDecimal;
import java.util.UUID;
import at.fhv.ecommerce.application.common.command.Command;

public record CreateProductCommand(
    UUID productId,
    String name,
    String description,
    BigDecimal price,
    Integer stock
)
    implements Command {
}
