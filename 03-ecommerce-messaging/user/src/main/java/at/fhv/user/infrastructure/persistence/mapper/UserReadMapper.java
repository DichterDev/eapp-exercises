package at.fhv.user.infrastructure.persistence.mapper;

import at.fhv.user.infrastructure.persistence.entity.CartItemEmbeddable;
import at.fhv.user.infrastructure.persistence.entity.UserEntity;
import at.fhv.user.projection.CartItem;
import at.fhv.user.projection.User;
import at.fhv.user.projection.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserReadMapper {

    @Mapping(target = "userId", source = "id")
    User toProjection(UserEntity entity);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "cart", source = "cart")
    UserDetail toDetail(UserEntity entity);

    default CartItem map(CartItemEmbeddable embeddable) {
        if (embeddable == null) return null;
        return new CartItem(
                embeddable.getProductId(),
                embeddable.getAmount()
        );
    }

    default List<CartItem> map(List<CartItemEmbeddable> list) {
        if (list == null) return null;
        return list.stream().map(this::map).toList();
    }
}