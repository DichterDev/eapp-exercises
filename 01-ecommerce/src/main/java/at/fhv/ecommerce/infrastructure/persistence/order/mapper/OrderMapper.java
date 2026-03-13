package at.fhv.ecommerce.infrastructure.persistence.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.infrastructure.persistence.order.entity.OrderEntity;
import at.fhv.ecommerce.infrastructure.persistence.user.mapper.UserIdMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
    OrderIdMapper.class,
    UserIdMapper.class,
    OrderItemMapper.class
})
public interface OrderMapper {
    Order toModel(OrderEntity entity);

    OrderEntity toEntity(Order model);
}
