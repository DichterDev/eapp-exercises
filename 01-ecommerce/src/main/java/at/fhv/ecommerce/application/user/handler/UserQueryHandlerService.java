package at.fhv.ecommerce.application.user.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import at.fhv.ecommerce.application.order.handler.OrderQueryHandler;
import at.fhv.ecommerce.application.order.query.GetOrdersByUserIdWithItemsQuery;
import at.fhv.ecommerce.application.user.query.GetUserByIdQuery;
import at.fhv.ecommerce.application.user.query.GetUserByIdWithCartQuery;
import at.fhv.ecommerce.application.user.query.GetUserOrdersById;
import at.fhv.ecommerce.application.user.query.GetUsersQuery;
import at.fhv.ecommerce.application.user.query.GetUsersWithCartQuery;
import at.fhv.ecommerce.application.user.view.CartItemView;
import at.fhv.ecommerce.application.user.view.UserDetailView;
import at.fhv.ecommerce.application.user.view.UserOrderView;
import at.fhv.ecommerce.application.user.view.UserView;
import at.fhv.ecommerce.domain.user.model.CartItem;
import at.fhv.ecommerce.domain.user.model.UserId;
import at.fhv.ecommerce.domain.user.ports.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerService implements UserQueryHandler {
    private final UserRepository repository;
    private final OrderQueryHandler order;

    @Override
    @Transactional
    public UserView handleGet(GetUserByIdQuery query) {
        var user = repository.findById(new UserId(query.id())).orElseThrow();

        return new UserView(
            user.getId().value(),
            user.getName()
        );
    }

    @Override
    @Transactional
    public UserDetailView handleGetWithCart(GetUserByIdWithCartQuery query) {
        var user = repository.findByIdWithCartItems(new UserId(query.id())).orElseThrow();
        List<CartItemView> cart = user
            .getCart()
            .stream()
            .map(item -> {
                return new CartItemView(item.productId().value(), item.amount());
            })
            .toList();

        return new UserDetailView(
            user.getId().value(),
            user.getName(),
            cart
        );
    }

    @Override
    public List<UserOrderView> handleGetOrders(GetUserOrdersById query) {
        var orders = order.handleGetByUserId(
            new GetOrdersByUserIdWithItemsQuery(query.userId(), query.page(), query.size())
        );

        return orders.stream().map(order -> {
            return new UserOrderView(order.id(), order.items());
        }).toList();
    }

    @Override
    public List<UserView> handleGetAll(GetUsersQuery query) {
        return repository.all(query.page(), query.size()).stream().map(user -> {
            return new UserView(user.getId().value(), user.getName());
        }).toList();
    }

    @Override
    public List<UserDetailView> handleGetAllDetail(GetUsersWithCartQuery query) {
        return repository.allWithCartItems(query.page(), query.size()).stream().map(user -> {
            return new UserDetailView(
                user.getId().value(),
                user.getName(),
                user.getCart().stream().map(item -> {
                    return new CartItemView(item.productId().value(), item.amount());
                }).toList()
            );
        }).toList();
    }
}
