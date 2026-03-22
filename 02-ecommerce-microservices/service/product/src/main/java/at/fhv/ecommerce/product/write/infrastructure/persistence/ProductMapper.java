package at.fhv.ecommerce.product.write.infrastructure.persistence;

import java.util.UUID;
import org.mapstruct.Mapper;
import at.fhv.ecommerce.common.infrastructure.persistence.MoneyMapper;
import at.fhv.ecommerce.product.write.domain.model.Product;
import at.fhv.ecommerce.product.write.domain.model.ProductId;

@Mapper(uses = {
    MoneyMapper.class
})
public interface ProductMapper {
    Product toModel(ProductEntity entity);

    ProductEntity toEntity(Product model);

    default ProductId map(UUID id) {
        return id != null ? new ProductId(id) : null;
    }

    default UUID map(ProductId id) {
        return id != null ? id.value() : null;
    }
}
