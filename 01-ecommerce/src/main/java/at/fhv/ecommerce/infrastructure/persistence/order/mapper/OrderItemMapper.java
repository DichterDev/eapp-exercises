package at.fhv.ecommerce.infrastructure.persistence.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.domain.order.model.OrderItem;
import at.fhv.ecommerce.infrastructure.persistence.common.mapper.MoneyMapper;
import at.fhv.ecommerce.infrastructure.persistence.order.entity.OrderItemEmbeddable;
import at.fhv.ecommerce.infrastructure.persistence.product.mapper.ProductIdMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
    ProductIdMapper.class,
    MoneyMapper.class
})
public interface OrderItemMapper {
    OrderItem toModel(OrderItemEmbeddable entity);

    OrderItemEmbeddable toEntity(OrderItem model);
}
