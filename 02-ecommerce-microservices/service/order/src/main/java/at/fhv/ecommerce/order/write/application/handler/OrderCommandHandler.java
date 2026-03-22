package at.fhv.ecommerce.order.write.application.handler;

import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.order.write.application.command.PlaceOrder;

public interface OrderCommandHandler {
    CommandResponse place(PlaceOrder cmd);
}
