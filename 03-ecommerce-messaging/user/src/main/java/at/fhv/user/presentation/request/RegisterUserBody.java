package at.fhv.user.presentation.request;

import java.util.UUID;

public record RegisterUserBody(
        UUID userId,
        String name
) {
}
