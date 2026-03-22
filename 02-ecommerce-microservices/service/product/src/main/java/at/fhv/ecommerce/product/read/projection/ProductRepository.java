package at.fhv.ecommerce.product.read.projection;

import java.util.UUID;

public interface ProductRepository {
    Product findById(UUID id);

    ProductDetail findDetailById(UUID id);
}
