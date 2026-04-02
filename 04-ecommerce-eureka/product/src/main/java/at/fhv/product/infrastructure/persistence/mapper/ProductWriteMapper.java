package at.fhv.product.infrastructure.persistence.mapper;

import at.fhv.common.domain.model.Money;
import at.fhv.product.domain.model.Product;
import at.fhv.product.domain.model.ProductId;
import at.fhv.product.infrastructure.persistence.entity.MoneyEmbeddable;
import at.fhv.product.infrastructure.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductWriteMapper {
    Product toDomain(ProductEntity entity);

    ProductEntity toEntity(Product product);

    default ProductId map(UUID id) {
        return id == null ? null : new ProductId(id);
    }

    default UUID map(ProductId id) {
        return id == null ? null : id.value();
    }

    default MoneyEmbeddable map(Money money) {
        return new MoneyEmbeddable(money.value(), money.currency().toString());
    }

    default Money map(MoneyEmbeddable money) {
        return new Money(money.getAmount());
    }
}
