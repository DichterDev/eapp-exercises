package at.fhv.ecommerce.infrastructure.persistence.product.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.domain.product.model.ProductId;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductIdMapper {

    default UUID map(ProductId id) {
        return id != null ? id.value() : null;
    }

    default ProductId map(UUID id) {
        return id != null ? new ProductId(id) : null;
    }
}
