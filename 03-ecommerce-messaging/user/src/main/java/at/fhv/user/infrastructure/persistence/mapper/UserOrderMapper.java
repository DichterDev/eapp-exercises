package at.fhv.user.infrastructure.persistence.mapper;

import at.fhv.user.infrastructure.persistence.entity.UserOrderEntity;
import at.fhv.user.projection.UserOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserOrderMapper {
    UserOrder toProjection(UserOrderEntity entity);
}