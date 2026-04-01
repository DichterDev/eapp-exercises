package at.fhv.common.domain.event.user;

import java.util.UUID;

public record UserCartCheckedOutItem(
        UUID productId,
        Integer amount,
        Double price
) {
}