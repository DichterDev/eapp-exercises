package at.fhv.ecommerce.presentation.user.response;

import java.util.List;
import java.util.UUID;

public record UserDetailResponse(UUID userId, String name, List<CartItemResponse> cart) {
}
