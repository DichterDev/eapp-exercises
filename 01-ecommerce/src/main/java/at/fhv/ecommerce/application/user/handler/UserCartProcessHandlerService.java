package at.fhv.ecommerce.application.user.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.application.order.command.PlaceOrderCommand;
import at.fhv.ecommerce.application.order.handler.OrderCommandHandler;
import at.fhv.ecommerce.application.user.command.CompleteUserCartCheckoutCommand;
import at.fhv.ecommerce.application.user.query.GetUserByIdWithCartQuery;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.event.UserCartCheckedOutEvent;
import at.fhv.ecommerce.domain.user.model.UserId;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCartProcessHandlerService implements UserCartProcessHandler {
    private final UserCommandHandler command;
    private final UserQueryHandler query;
    private final OrderCommandHandler order;

    @Override
    @EventListener
    public void handle(UserCartCheckedOutEvent event) {
        try {
            var user = query.handleGetWithCart(
                new GetUserByIdWithCartQuery(event.userId().value())
            );

            Map<UUID, Integer> cart = new HashMap<>();

            user.cart().forEach(item -> {
                UUID pId = item.productId();
                if (cart.containsKey(pId)) {
                    cart.put(pId, cart.get(pId) + item.amount());
                } else {
                    cart.put(pId, item.amount());
                }
            });

            order.handlePlace(new PlaceOrderCommand(event.orderId().value(), user.id(), cart));

            command.handleCompleteCartCheckout(new CompleteUserCartCheckoutCommand(user.id()));
        } catch (Exception ex) {

        }
    }
}
