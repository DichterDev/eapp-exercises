package at.fhv.product.application.handler;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.product.application.command.ChangeProductPrice;
import at.fhv.product.application.command.CreateProduct;
import at.fhv.product.application.command.IncreaseProductStock;
import at.fhv.product.application.command.ReduceProductStock;

public interface ProductCommandHandler {

    CommandResponse create(CreateProduct cmd);

    void updatePrice(ChangeProductPrice cmd);

    void reduceStock(ReduceProductStock cmd);

    void increaseStock(IncreaseProductStock cmd);

}
