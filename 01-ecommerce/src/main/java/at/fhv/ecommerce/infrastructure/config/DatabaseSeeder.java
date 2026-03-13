package at.fhv.ecommerce.infrastructure.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.order.command.PlaceOrderCommand;
import at.fhv.ecommerce.application.order.handler.OrderCommandHandler;
import at.fhv.ecommerce.application.order.handler.OrderQueryHandler;
import at.fhv.ecommerce.application.order.query.GetOrderByIdWithItemsQuery;
import at.fhv.ecommerce.application.product.command.CreateProductCommand;
import at.fhv.ecommerce.application.product.handler.ProductCommandHandler;
import at.fhv.ecommerce.application.product.handler.ProductQueryHandler;
import at.fhv.ecommerce.application.product.query.GetProductByIdQuery;
import at.fhv.ecommerce.application.user.command.RegisterUserCommand;
import at.fhv.ecommerce.application.user.handler.UserCommandHandler;
import at.fhv.ecommerce.application.user.handler.UserQueryHandler;
import at.fhv.ecommerce.application.user.query.GetUserByIdQuery;
import at.fhv.ecommerce.domain.common.Money;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.order.ports.OrderRepository;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.model.UserId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
    private final OrderCommandHandler orderCommand;
    private final UserCommandHandler userCommand;
    private final ProductCommandHandler productCommand;

    private final ProductQueryHandler productQuery;
    private final UserQueryHandler userQuery;
    private final OrderQueryHandler orderQuery;

    @Override
    public void run(String... args) throws Exception {
        CommandResponse uRes = userCommand.handleRegister(new RegisterUserCommand("test"));
        CommandResponse pRes = productCommand.handleCreate(
            new CreateProductCommand(
                "P1", "", new Money(BigDecimal.TEN, Currency.getInstance("EUR")), 10
            )
        );

        UserId uId = new UserId(UUID.fromString(uRes.id()));
        ProductId pId = new ProductId(UUID.fromString(pRes.id()));

        HashMap<ProductId, Integer> orderItems = new HashMap<>();
        orderItems.put(pId, 5);

        CommandResponse oRes = orderCommand.handlePlace(new PlaceOrderCommand(uId, orderItems));

        OrderId oId = new OrderId(UUID.fromString(oRes.id()));

        var uView = userQuery.handleGet(new GetUserByIdQuery(uId.value()));
        var pView = productQuery.handleGet(new GetProductByIdQuery(pId.value()));
        var oView = orderQuery.handleGetWithItems(new GetOrderByIdWithItemsQuery(oId.value()));

        System.out.println(uView.toString());
        System.out.println(pView.toString());
        System.out.println(oView.toString());
    }
}
