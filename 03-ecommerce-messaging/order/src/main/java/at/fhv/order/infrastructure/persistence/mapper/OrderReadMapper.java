package at.fhv.order.infrastructure.persistence.mapper;

import at.fhv.order.infrastructure.persistence.entity.OrderEntity;
import at.fhv.order.infrastructure.persistence.entity.OrderItemEmbeddable;
import at.fhv.order.projection.Order;
import at.fhv.order.projection.OrderDetail;
import at.fhv.order.projection.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderReadMapper {

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "status", expression = "java(entity.getStatus().name())")
    Order toProjection(OrderEntity entity);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "orderTotal", expression = "java(calculateTotal(entity.getItems()))")
    @Mapping(target = "status", expression = "java(entity.getStatus().name())")
    OrderDetail toDetail(OrderEntity entity);

    default OrderItem map(OrderItemEmbeddable embeddable) {
        if (embeddable == null) return null;

        Double unitPrice = embeddable.getPrice() != null
                ? embeddable.getPrice().getAmount().doubleValue()
                : null;

        Double lineTotal = (unitPrice != null && embeddable.getAmount() != null)
                ? embeddable.getAmount() * unitPrice
                : null;

        return new OrderItem(
                embeddable.getProductId(),
                embeddable.getAmount(),
                unitPrice,
                lineTotal
        );
    }

    default List<OrderItem> map(List<OrderItemEmbeddable> list) {
        if (list == null) return null;
        return list.stream().map(this::map).toList();
    }

    default Double calculateTotal(List<OrderItemEmbeddable> items) {
        if (items == null) return null;

        return items.stream()
                .map(this::map)
                .map(OrderItem::lineTotal)
                .filter(java.util.Objects::nonNull)
                .reduce(0.0, Double::sum);
    }
}
