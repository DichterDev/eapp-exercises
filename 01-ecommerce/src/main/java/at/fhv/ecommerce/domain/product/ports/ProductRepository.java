package at.fhv.ecommerce.domain.product.ports;

import at.fhv.ecommerce.domain.common.BaseRepository;
import at.fhv.ecommerce.domain.product.model.Product;
import at.fhv.ecommerce.domain.product.model.ProductId;

public interface ProductRepository extends BaseRepository<Product, ProductId> {
}
