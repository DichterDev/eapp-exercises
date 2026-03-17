package at.fhv.ecommerce.presentation.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.order.view.OrderItemView;
import at.fhv.ecommerce.application.user.view.UserDetailView;
import at.fhv.ecommerce.application.user.view.UserOrderView;
import at.fhv.ecommerce.application.user.view.UserView;
import at.fhv.ecommerce.presentation.order.response.OrderItemResponse;
import at.fhv.ecommerce.presentation.user.response.UserDetailResponse;
import at.fhv.ecommerce.presentation.user.response.UserIdResponse;
import at.fhv.ecommerce.presentation.user.response.UserOrderResponse;
import at.fhv.ecommerce.presentation.user.response.UserResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserResponseMapper {
    @Mapping(target = "userId", source = "id")
    UserResponse toResponse(UserView view);

    @Mapping(target = "userId", source = "id")
    UserDetailResponse toResponse(UserDetailView view);

    @Mapping(target = "userId", source = "id")
    UserIdResponse toResponse(CommandResponse res);

    UserOrderResponse toResponse(UserOrderView view);

    OrderItemResponse toResponse(OrderItemView view);
}
