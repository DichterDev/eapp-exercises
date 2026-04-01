package at.fhv.user.infrastructure.messaging;

import at.fhv.common.domain.event.order.OrderPlaced;
import at.fhv.user.application.command.RegisterUserOrder;
import at.fhv.user.application.handler.UserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderPlacedConsumer {
    private final UserCommandHandler command;

    @Bean
    public Consumer<OrderPlaced> orderPlacedIn() {
        return event -> command.registerUserOrder(
                new RegisterUserOrder(
                        event.orderId(),
                        event.userId()
                )
        );
    }

}
