package at.fhv.user.infrastructure.messaging.consumer;

import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import at.fhv.common.domain.event.order.OrderPlaced;
import at.fhv.user.application.command.CompleteUserCartCheckout;
import at.fhv.user.application.handler.UserCommandHandler;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OrderConsumer {
    private final UserCommandHandler command;

    @Bean
    public Consumer<OrderPlaced> orderPlacedIn() {
        return event -> command.completeCartCheckout(
            new CompleteUserCartCheckout(event.userId())
        );
    }
}
