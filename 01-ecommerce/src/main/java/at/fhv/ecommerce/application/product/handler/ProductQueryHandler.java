package at.fhv.ecommerce.application.product.handler;

import at.fhv.ecommerce.application.product.query.CheckIfProductExistsQuery;
import at.fhv.ecommerce.application.product.query.GetProductByIdQuery;
import at.fhv.ecommerce.application.product.view.ProductView;

public interface ProductQueryHandler {
    ProductView handleGet(GetProductByIdQuery query);

    boolean checkIfExists(CheckIfProductExistsQuery query);
}
