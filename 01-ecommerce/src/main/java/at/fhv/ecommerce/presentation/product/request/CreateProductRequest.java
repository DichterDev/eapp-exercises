package at.fhv.ecommerce.presentation.product.request;

public record CreateProductRequest(
    String name, String description, Integer stock, Float price, String currency
) {
}
