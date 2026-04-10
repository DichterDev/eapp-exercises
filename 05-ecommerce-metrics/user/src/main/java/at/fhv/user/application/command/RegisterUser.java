package at.fhv.user.application.command;

import java.util.UUID;

public record RegisterUser(UUID userId, String name) {
}