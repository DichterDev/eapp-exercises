package at.fhv.user.projection;

import java.util.UUID;

public record User(UUID userId, String name) {
}