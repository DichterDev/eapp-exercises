package at.fhv.ecommerce.order.read.projection;

public record Order(String orderId, String userId, String status) {
}
