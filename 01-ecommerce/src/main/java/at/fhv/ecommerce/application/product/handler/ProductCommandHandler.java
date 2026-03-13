package at.fhv.ecommerce.application.product.handler;

import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.product.command.CreateProductCommand;
import at.fhv.ecommerce.application.product.command.IncreaseProductStockCommand;
import at.fhv.ecommerce.application.product.command.ReduceProductStockCommand;
import at.fhv.ecommerce.application.product.command.UpdateProductPriceCommand;

public interface ProductCommandHandler {
    CommandResponse handleCreate(CreateProductCommand cmd);

    void handleIncreaseStock(IncreaseProductStockCommand cmd);

    void handleReduceStock(ReduceProductStockCommand cmd);

    void handlePriceUpdate(UpdateProductPriceCommand cmd);
}
