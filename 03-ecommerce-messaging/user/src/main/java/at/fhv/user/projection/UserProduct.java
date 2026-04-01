package at.fhv.user.projection;

import java.math.BigDecimal;
import java.util.UUID;

public record UserProduct(UUID productId,
                          BigDecimal price) {
}
