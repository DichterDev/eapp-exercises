package at.fhv.user.infrastructure.persistence.mapper;

import at.fhv.common.domain.model.Money;
import at.fhv.user.domain.model.*;
import at.fhv.user.infrastructure.persistence.entity.CartItemEmbeddable;
import at.fhv.user.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserWriteMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "cart", source = "cart")
    User toDomain(UserEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "cart", source = "cart")
    UserEntity toEntity(User user);

    default UserId mapUID(UUID id) {
        return id == null ? null : new UserId(id);
    }

    default OrderId mapOID(UUID id) {
        return id == null ? null : new OrderId(id);
    }

    default ProductId mapPID(UUID id) {
        return id == null ? null : new ProductId(id);
    }

    default UUID map(UserId id) {
        return id == null ? null : id.value();
    }

    default UUID map(OrderId id) {
        return id == null ? null : id.value();
    }

    default UUID map(ProductId id) {
        return id == null ? null : id.value();
    }

    default CartItem map(CartItemEmbeddable embeddable) {
        if (embeddable == null) return null;
        return new CartItem(
                new ProductId(embeddable.getProductId()),
                embeddable.getAmount(),
                new Money(BigDecimal.valueOf(embeddable.getPrice()))
        );
    }

    default CartItemEmbeddable map(CartItem item) {
        if (item == null) return null;
        return new CartItemEmbeddable(
                item.productId().value(),
                item.amount(),
                item.price().value().doubleValue()
        );
    }

    default List<CartItem> mapCartToDomain(List<CartItemEmbeddable> list) {
        if (list == null) return null;
        return list.stream().map(this::map).toList();
    }

    default List<CartItemEmbeddable> mapCartToEntity(List<CartItem> list) {
        if (list == null) return null;
        return list.stream().map(this::map).toList();
    }
}