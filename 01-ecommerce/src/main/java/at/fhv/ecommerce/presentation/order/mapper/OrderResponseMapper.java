package at.fhv.ecommerce.presentation.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.order.view.OrderDetailsView;
import at.fhv.ecommerce.application.order.view.OrderItemView;
import at.fhv.ecommerce.application.order.view.OrderView;
import at.fhv.ecommerce.presentation.order.response.OrderDetailResponse;
import at.fhv.ecommerce.presentation.order.response.OrderIdResponse;
import at.fhv.ecommerce.presentation.order.response.OrderItemResponse;
import at.fhv.ecommerce.presentation.order.response.OrderResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderResponseMapper {
    @Mapping(target = "orderId", source = "id")
    OrderResponse toResponse(OrderView view);

    @Mapping(target = "orderId", source = "id")
    OrderIdResponse toResponse(CommandResponse res);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "orderItems", source = "items")
    OrderDetailResponse toResponse(OrderDetailsView view);

    OrderItemResponse toResponse(OrderItemView view);
}
