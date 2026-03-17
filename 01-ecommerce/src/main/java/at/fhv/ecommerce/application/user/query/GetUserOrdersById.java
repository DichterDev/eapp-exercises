package at.fhv.ecommerce.application.user.query;

import java.util.UUID;

public record GetUserOrdersById(UUID userId, Integer page, Integer size) {
}
