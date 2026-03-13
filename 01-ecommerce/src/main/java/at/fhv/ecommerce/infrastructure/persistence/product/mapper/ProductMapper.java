package at.fhv.ecommerce.infrastructure.persistence.product.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.domain.product.model.Product;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.infrastructure.persistence.common.mapper.MoneyMapper;
import at.fhv.ecommerce.infrastructure.persistence.product.entity.ProductEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
    MoneyMapper.class,
    ProductIdMapper.class
})
public interface ProductMapper {
    Product toModel(ProductEntity entity);

    ProductEntity toEntity(Product model);
}
