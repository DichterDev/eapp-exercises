package at.fhv.ecommerce.order.write.infrastructure.persistence;

import java.util.UUID;
import org.mapstruct.Mapper;
import at.fhv.ecommerce.common.infrastructure.persistence.MoneyMapper;
import at.fhv.ecommerce.order.write.domain.model.Order;
import at.fhv.ecommerce.order.write.domain.model.OrderId;
import at.fhv.ecommerce.order.write.domain.model.ProductId;
import at.fhv.ecommerce.order.write.domain.model.UserId;

@Mapper(uses = {MoneyMapper.class})
public interface OrderMapper {
    Order toModel(OrderEntity entity);

    OrderEntity toEntity(Order order);

    default UUID map(ProductId id) {
        return id != null ? id.value() : null;
    }

    default UUID map(UserId id) {
        return id != null ? id.value() : null;
    }

    default UUID map(OrderId id) {
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
}
