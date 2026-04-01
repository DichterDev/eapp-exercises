package at.fhv.user.infrastructure.messaging;

import at.fhv.common.domain.event.order.OrderCompleted;
import at.fhv.user.application.command.CompleteUserCartCheckout;
import at.fhv.user.application.command.CompleteUserOrder;
import at.fhv.user.application.handler.UserCommandHandler;
import at.fhv.user.domain.port.UserOrderReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderCompletedConsumer {
    private final UserCommandHandler command;
    private final UserOrderReadRepository orderReadRepository;

    @Bean
    public Consumer<OrderCompleted> orderCompletedIn() {
        return event -> {
            var userOrder = orderReadRepository.findByOrderId(event.orderId())
                    .orElseThrow();

            command.completeUserOrder(
                    new CompleteUserOrder(event.orderId())
            );

            command.completeCartCheckout(
                    new CompleteUserCartCheckout(userOrder.userId())
            );
        };
    }

}