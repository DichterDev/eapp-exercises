package at.fhv.ecommerce.application.order.handler;

import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.order.command.CancelOrderCommand;
import at.fhv.ecommerce.application.order.command.CompleteOrderCommand;
import at.fhv.ecommerce.application.order.command.FailOrderCommand;
import at.fhv.ecommerce.application.order.command.PlaceOrderCommand;
import at.fhv.ecommerce.domain.order.model.OrderId;

public interface OrderCommandHandler {
    CommandResponse handlePlace(PlaceOrderCommand cmd);

    void handleComplete(CompleteOrderCommand cmd);

    void handleFail(FailOrderCommand cmd);

    void handleCancel(CancelOrderCommand cmd);
}
