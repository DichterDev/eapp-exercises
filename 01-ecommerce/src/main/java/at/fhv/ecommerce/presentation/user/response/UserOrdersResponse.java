package at.fhv.ecommerce.presentation.user.response;

import java.util.List;

public record UserOrdersResponse(List<UserOrderResponse> orders) {
}
