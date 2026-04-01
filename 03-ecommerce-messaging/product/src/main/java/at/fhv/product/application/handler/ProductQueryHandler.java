package at.fhv.product.application.handler;

import at.fhv.product.application.query.GetProductById;
import at.fhv.product.application.query.GetProductDetailById;
import at.fhv.product.projection.Product;
import at.fhv.product.projection.ProductDetail;

import java.util.UUID;

public interface ProductQueryHandler {
    Product get(GetProductById query);

    ProductDetail getDetail(GetProductDetailById query);
}
