package at.fhv.ecommerce.product.write.application.handler;

import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.product.write.application.command.ChangeProductPrice;
import at.fhv.ecommerce.product.write.application.command.CreateProduct;
import at.fhv.ecommerce.product.write.application.command.IncreaseProductStock;
import at.fhv.ecommerce.product.write.application.command.ReduceProductStock;

public interface ProductCommandHandler {
    CommandResponse create(CreateProduct cmd);

    void updatePrice(ChangeProductPrice cmd);

    void reduceStock(ReduceProductStock cmd);

    void increaseStock(IncreaseProductStock cmd);
}
