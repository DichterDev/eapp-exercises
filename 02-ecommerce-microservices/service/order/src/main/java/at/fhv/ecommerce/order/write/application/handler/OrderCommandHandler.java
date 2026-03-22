package at.fhv.ecommerce.order.write.application.handler;

import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.order.write.application.command.CancelOrder;
import at.fhv.ecommerce.order.write.application.command.CompleteOrder;
import at.fhv.ecommerce.order.write.application.command.FailOrder;
import at.fhv.ecommerce.order.write.application.command.PlaceOrder;

public interface OrderCommandHandler {
    CommandResponse place(PlaceOrder cmd);

    void complete(CompleteOrder cmd);

    void fail(FailOrder cmd);

    void cancel(CancelOrder cmd);
}
