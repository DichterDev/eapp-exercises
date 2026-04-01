package at.fhv.user.application.command;

import java.util.UUID;

public record ChangeUserName(UUID userId, String newName) {
}