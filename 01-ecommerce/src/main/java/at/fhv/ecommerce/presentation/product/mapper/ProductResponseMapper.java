package at.fhv.ecommerce.presentation.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.product.view.ProductView;
import at.fhv.ecommerce.presentation.product.response.ProductIdResponse;
import at.fhv.ecommerce.presentation.product.response.ProductResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductResponseMapper {
    @Mapping(target = "productId", source = "id")
    ProductResponse toResponse(ProductView view);

    @Mapping(target = "productId", source = "id")
    ProductIdResponse toResponse(CommandResponse res);
}
