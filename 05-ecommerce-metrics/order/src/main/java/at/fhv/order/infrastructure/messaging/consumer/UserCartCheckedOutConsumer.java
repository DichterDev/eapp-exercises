package at.fhv.order.infrastructure.messaging.consumer;

import at.fhv.common.domain.event.user.UserCartCheckedOut;
import at.fhv.order.application.command.PlaceOrder;
import at.fhv.order.application.handler.OrderCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class UserCartCheckedOutConsumer {

    private final OrderCommandHandler command;

    @Bean
    public Consumer<UserCartCheckedOut> userEventsIn() {
        return event -> {
            command.place(
                new PlaceOrder(
                    event.id(),
                    event.userId(),
                    event.items()
                )
            );
        };
    }
}
