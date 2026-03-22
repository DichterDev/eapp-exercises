package at.fhv.ecommerce.product.read.application.handler;

import at.fhv.ecommerce.product.read.application.query.GetProductById;
import at.fhv.ecommerce.product.read.application.query.GetProductDetailById;
import at.fhv.ecommerce.product.read.projection.Product;
import at.fhv.ecommerce.product.read.projection.ProductDetail;

public interface ProductQueryHandler {
    Product get(GetProductById query);

    ProductDetail getDetail(GetProductDetailById query);
}
