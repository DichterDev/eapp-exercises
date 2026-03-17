package at.fhv.ecommerce.application.order.query;

import java.util.UUID;

public record GetOrdersByUserIdWithItemsQuery(UUID userId, Integer page, Integer size) {
}
