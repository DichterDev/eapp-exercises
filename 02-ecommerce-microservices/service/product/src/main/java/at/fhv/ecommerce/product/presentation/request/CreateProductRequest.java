package at.fhv.ecommerce.product.presentation.request;

public record CreateProductRequest(String name, String description, Double price, Integer stock) {
}
