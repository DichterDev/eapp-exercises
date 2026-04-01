package at.fhv.order.infrastructure.messaging;

import at.fhv.common.domain.event.product.ProductReducedStock;
import at.fhv.order.application.command.RegisterReducedStock;
import at.fhv.order.application.handler.OrderCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class ProductReducedStockConsumer {
    private final OrderCommandHandler command;

    @Bean
    public Consumer<ProductReducedStock> productReducedStockIn() {
        return event -> {
            command.registerReducedStock(new RegisterReducedStock(event.orderId()));
        };
    }
}
