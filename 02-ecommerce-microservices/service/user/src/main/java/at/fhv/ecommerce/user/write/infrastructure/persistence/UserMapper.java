package at.fhv.ecommerce.user.write.infrastructure.persistence;

import java.util.UUID;
import org.mapstruct.Mapper;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;

@Mapper
public interface UserMapper {
    User toModel(UserEntity entity);

    UserEntity toEntity(User model);

    default UserId map(UUID id) {
        return id != null ? new UserId(id) : null;
    }

    default UUID map(UserId id) {
        return id != null ? id.value() : null;
    }
}
