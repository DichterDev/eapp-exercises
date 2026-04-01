package at.fhv.user.infrastructure.messaging;

import at.fhv.common.domain.event.order.OrderFailed;
import at.fhv.user.application.command.FailUserOrder;
import at.fhv.user.application.handler.UserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderFailedConsumer {
    private final UserCommandHandler command;

    @Bean
    public Consumer<OrderFailed> orderFailedIn() {
        return event -> command.failUserOrder(
                new FailUserOrder(event.orderId())
        );
    }
}