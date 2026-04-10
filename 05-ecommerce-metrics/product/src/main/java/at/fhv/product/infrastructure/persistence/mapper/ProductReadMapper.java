package at.fhv.product.infrastructure.persistence.mapper;

import at.fhv.product.projection.Product;
import at.fhv.product.infrastructure.persistence.entity.ProductEntity;
import at.fhv.product.projection.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductReadMapper {
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "price", source = "price.amount")
    @Mapping(target = "currency", source = "price.currency")
    Product toProjection(ProductEntity entity);

    @Mapping(target = "productId", source = "id")
    @Mapping(target = "price", source = "price.amount")
    @Mapping(target = "currency", source = "price.currency")
    ProductDetail toDetail(ProductEntity entity);
}
