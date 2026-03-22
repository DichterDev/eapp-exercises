package at.fhv.ecommerce.user.write.infrastructure.persistence;

import java.util.UUID;
import org.mapstruct.Mapper;
import at.fhv.ecommerce.user.write.domain.model.ProductId;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;

@Mapper
public interface UserMapper {
    User toModel(UserEntity entity);

    UserEntity toEntity(User model);

    default UserId mapUId(UUID id) {
        return id != null ? new UserId(id) : null;
    }

    default ProductId mapPId(UUID id) {
        return id != null ? new ProductId(id) : null;
    }

    default UUID map(UserId id) {
        return id != null ? id.value() : null;
    }

    default UUID map(ProductId id) {
        return id != null ? id.value() : null;
    }
}
