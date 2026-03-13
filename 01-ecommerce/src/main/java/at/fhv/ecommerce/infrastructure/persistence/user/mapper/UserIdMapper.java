package at.fhv.ecommerce.infrastructure.persistence.user.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.domain.user.model.UserId;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserIdMapper {
    default UUID map(UserId id) {
        return id != null ? id.value() : null;
    }

    default UserId map(UUID id) {
        return id != null ? new UserId(id) : null;
    }
}
