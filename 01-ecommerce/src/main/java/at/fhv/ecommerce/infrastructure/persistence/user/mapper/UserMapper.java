package at.fhv.ecommerce.infrastructure.persistence.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.infrastructure.persistence.product.mapper.ProductIdMapper;
import at.fhv.ecommerce.infrastructure.persistence.user.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
    UserIdMapper.class,
    ProductIdMapper.class
})
public interface UserMapper {
    UserEntity toEntity(User user);

    User toModel(UserEntity user);
}
