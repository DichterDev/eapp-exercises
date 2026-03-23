package at.fhv.ecommerce.gateway.presentation.request;

public record CreateProductRequest(String name, String description, Float price, Integer stock) {
}
