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
import at.fhv.ecommerce.application.user.command.AddItemToUserCartCommand;
import at.fhv.ecommerce.application.user.command.CheckoutUserCartCommand;
import at.fhv.ecommerce.application.user.command.CompleteUserCartCheckoutCommand;
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
        // USER
        var uID = UUID.randomUUID();

        userCommand.handleRegister(new RegisterUserCommand(uID, "Test User"));

        // PRODUCT
        var pID = UUID.randomUUID();

        productCommand.handleCreate(
            new CreateProductCommand(pID, "P1", "Product 1", new BigDecimal(7.5), 100)
        );

        // ORDER
        var oID = UUID.randomUUID();

        orderCommand.handlePlace(new PlaceOrderCommand(oID, uID, Map.of(pID, 10)));

        // ADD TO USER CART
        userCommand.handleAddItem(new AddItemToUserCartCommand(uID, pID, 20));

        // CHECKOUT CART
        UUID checkoutOrder = UUID.randomUUID();
        userCommand.handleCartCheckout(new CheckoutUserCartCommand(uID, checkoutOrder));

        System.out.println("User ID:");
        System.out.println(uID);
        System.out.println("Product ID:");
        System.out.println(pID);
        System.out.println("Order ID:");
        System.out.println(oID);
        System.out.println("Checkout Order ID:");
        System.out.println(checkoutOrder);
    }
}
