package at.fhv.ecommerce.application.user.command;

import java.util.UUID;
import at.fhv.ecommerce.application.common.command.Command;

public record AddItemToUserCartCommand(UUID userId, UUID productId, Integer amount)
    implements Command {
}
