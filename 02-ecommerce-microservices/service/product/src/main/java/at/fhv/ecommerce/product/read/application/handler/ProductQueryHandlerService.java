package at.fhv.ecommerce.product.read.application.handler;

import org.springframework.stereotype.Service;
import at.fhv.ecommerce.product.read.application.query.GetProductById;
import at.fhv.ecommerce.product.read.application.query.GetProductDetailById;
import at.fhv.ecommerce.product.read.projection.Product;
import at.fhv.ecommerce.product.read.projection.ProductDetail;
import at.fhv.ecommerce.product.read.projection.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductQueryHandlerService implements ProductQueryHandler {
    private final ProductRepository repository;

    @Override
    public Product get(GetProductById query) {
        return repository.findById(query.productId());
    }

    @Override
    public ProductDetail getDetail(GetProductDetailById query) {
        return repository.findDetailById(query.productId());
    }

}
