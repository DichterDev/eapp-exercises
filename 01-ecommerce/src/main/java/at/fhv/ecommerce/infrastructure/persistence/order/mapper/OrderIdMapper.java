package at.fhv.ecommerce.infrastructure.persistence.order.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.domain.order.model.OrderId;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderIdMapper {
    default UUID map(OrderId id) {
        return id != null ? id.value() : null;
    }

    default OrderId map(UUID id) {
        return id != null ? new OrderId(id) : null;
    }
}
