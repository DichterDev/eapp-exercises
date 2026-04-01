package at.fhv.product.domain.port;

import at.fhv.product.projection.Product;
import at.fhv.product.projection.ProductDetail;

import java.util.Optional;
import java.util.UUID;

public interface ProductReadRepository {

    Optional<Product> get(UUID id);

    Optional<ProductDetail> getDetail(UUID id);

}
