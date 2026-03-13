package at.fhv.ecommerce.application.order.query;

import java.util.UUID;
import at.fhv.ecommerce.application.common.query.Query;

public record GetOrderByIdQuery(UUID id) implements Query {
}
