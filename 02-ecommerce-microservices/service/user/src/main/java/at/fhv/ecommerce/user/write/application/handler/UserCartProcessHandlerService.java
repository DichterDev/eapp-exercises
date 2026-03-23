package at.fhv.ecommerce.user.write.application.handler;

import at.fhv.ecommerce.user.read.application.handler.UserQueryHandler;
import at.fhv.ecommerce.user.read.application.query.GetUserDetailById;
import at.fhv.ecommerce.user.write.application.client.OrderClient;
import at.fhv.ecommerce.user.write.application.command.CompleteUserCartCheckout;
import at.fhv.ecommerce.user.write.domain.event.UserCartCheckedOut;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCartProcessHandlerService {
    private final UserQueryHandler query;
    private final UserCommandHandler command;
    private final OrderClient orderClient;

    @EventListener
    public void handle(UserCartCheckedOut event) {
        try {
            var user = query.getDetail(new GetUserDetailById(event.userId().value()));

            Map<UUID, Integer> items = new HashMap<>();
            user.cart().forEach(item -> {
                items.merge(UUID.fromString(item.productId()), item.amount(), Integer::sum);
            });

            orderClient.placeOrder(event.orderId().value(), event.userId().value(), items);

            command.completeCheckout(new CompleteUserCartCheckout(event.userId().value()));

        } catch (Exception ex) {
            System.err.println("Failed to trigger order placement: " + ex.getMessage());
        }
    }
}
