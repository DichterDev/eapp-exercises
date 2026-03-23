package at.fhv.ecommerce.gateway.presentation.command;

import java.util.UUID;

public record CreateProductCommand(
    UUID id,
    String name,
    String description,
    Float price,
    Integer stock
) {
}
