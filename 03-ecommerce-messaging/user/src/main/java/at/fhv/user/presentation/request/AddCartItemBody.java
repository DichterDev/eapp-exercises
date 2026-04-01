package at.fhv.user.presentation.request;

import java.util.UUID;

public record AddCartItemBody(
        UUID productId,
        int amount
) {
}