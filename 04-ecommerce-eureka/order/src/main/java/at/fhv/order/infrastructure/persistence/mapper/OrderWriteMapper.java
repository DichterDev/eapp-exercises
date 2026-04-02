package at.fhv.order.infrastructure.persistence.mapper;

import at.fhv.common.domain.model.Money;
import at.fhv.order.domain.model.*;
import at.fhv.order.infrastructure.persistence.entity.MoneyEmbeddable;
import at.fhv.order.infrastructure.persistence.entity.OrderEntity;
import at.fhv.order.infrastructure.persistence.entity.OrderItemEmbeddable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderWriteMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "reducedItemsCount", source = "reducedItemsCount")
    OrderEntity toEntity(Order order);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "reducedItemsCount", source = "reducedItemsCount")
    Order toDomain(OrderEntity entity);

    default UUID map(OrderId id) {
        return id != null ? id.value() : null;
    }

    default UUID map(UserId id) {
        return id != null ? id.value() : null;
    }

    default UUID map(ProductId id) {
        return id != null ? id.value() : null;
    }

    default OrderId mapOId(UUID id) {
        return id != null ? new OrderId(id) : null;
    }

    default UserId mapUId(UUID id) {
        return id != null ? new UserId(id) : null;
    }

    default ProductId mapPId(UUID id) {
        return id != null ? new ProductId(id) : null;
    }

    default OrderItemEmbeddable map(OrderItem item) {
        if (item == null)
            return null;

        return new OrderItemEmbeddable(
            item.getProductId() != null ? item.getProductId().value() : null,
            item.getAmount(),
            item.getPrice() != null
                ? new MoneyEmbeddable(
                    item.getPrice().value(),
                    item.getPrice().currency().getCurrencyCode()
                )
                : null
        );
    }

    default OrderItem map(OrderItemEmbeddable embeddable) {
        if (embeddable == null)
            return null;

        return OrderItem.builder()
            .productId(
                embeddable.getProductId() != null ? new ProductId(embeddable.getProductId()) : null
            )
            .amount(embeddable.getAmount())
            .price(map(embeddable.getPrice()))
            .build();
    }

    default List<OrderItemEmbeddable> mapItems(List<OrderItem> items) {
        return items == null ? null : items.stream().map(this::map).collect(Collectors.toList());
    }

    default List<OrderItem> mapEmbeddables(List<OrderItemEmbeddable> items) {
        return items == null ? null : items.stream().map(this::map).collect(Collectors.toList());
    }

    default Money map(MoneyEmbeddable money) {
        return new Money(money.getAmount());
    }

    default MoneyEmbeddable map(Money money) {
        return new MoneyEmbeddable(money.value(), money.currency().toString());
    }
}
