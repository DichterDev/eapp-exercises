package at.fhv.ecommerce.application.order.handler;

import java.util.List;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.application.order.query.GetOrderByIdQuery;
import at.fhv.ecommerce.application.order.query.GetOrderByIdWithItemsQuery;
import at.fhv.ecommerce.application.order.view.OrderDetailsView;
import at.fhv.ecommerce.application.order.view.OrderItemView;
import at.fhv.ecommerce.application.order.view.OrderView;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.order.ports.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderQueryHandlerService implements OrderQueryHandler {
    private final OrderRepository repository;

    @Override
    @Transactional
    public OrderView handleGet(GetOrderByIdQuery query) {
        var order = repository.findById(new OrderId(query.id())).orElseThrow();

        return new OrderView(
            order.getId().value(),
            order.getUserId().value(),
            order.getStatus().toString()
        );
    }

    @Override
    @Transactional
    public OrderDetailsView handleGetWithItems(GetOrderByIdWithItemsQuery query) {
        var order = repository.findByIdWithItems(new OrderId(query.id())).orElseThrow();
        List<OrderItemView> items = order.getItems()
            .stream()
            .map(item -> {
                return new OrderItemView(item.getProductId().value(), item.getAmount());
            })
            .toList();

        return new OrderDetailsView(
            order.getId().value(),
            order.getUserId().value(),
            order.getStatus().toString(),
            items
        );
    }
}
