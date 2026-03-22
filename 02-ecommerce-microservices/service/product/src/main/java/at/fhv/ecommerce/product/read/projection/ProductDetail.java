package at.fhv.ecommerce.product.read.projection;

public record ProductDetail(
    String productId,
    String name,
    String description,
    Float price,
    String currency
) {
}
