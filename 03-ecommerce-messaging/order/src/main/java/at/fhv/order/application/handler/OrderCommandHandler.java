package at.fhv.order.application.handler;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.order.application.command.*;

public interface OrderCommandHandler {
    CommandResponse place(PlaceOrder cmd);

    void complete(CompleteOrder cmd);

    void fail(FailOrder cmd);

    void cancel(CancelOrder cmd);

    void registerReducedStock(RegisterReducedStock cmd);
}