package at.fhv.order.domain.model;

import at.fhv.common.domain.event.order.OrderCancelled;
import at.fhv.common.domain.event.order.OrderCompleted;
import at.fhv.common.domain.event.order.OrderFailed;
import at.fhv.common.domain.event.order.OrderPlaced;
import at.fhv.common.domain.event.user.UserCartCheckedOutItem;
import at.fhv.common.domain.model.BaseDomainRoot;
import at.fhv.common.domain.model.Money;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Order extends BaseDomainRoot {
    private final OrderId id;
    private final UserId userId;

    @Builder.Default
    private Status status = Status.PENDING;

    @Builder.Default
    List<OrderItem> items = new ArrayList<>();

    @Builder.Default
    private Integer reducedItemsCount = 0;

    public enum Status {
        PENDING, COMPLETED, FAILED, CANCELLED
    }

    public static Order place(OrderId id, UserId userId, List<UserCartCheckedOutItem> orderItems) {
        var order = Order.builder()
                .id(id)
                .userId(userId)
                .status(Status.PENDING)
                .items(new ArrayList<>())
                .reducedItemsCount(0)
                .build();

        var groupedItems = orderItems == null
                ? Map.<UUID, UserCartCheckedOutItem>of()
                : orderItems.stream()
                .collect(Collectors.toMap(
                        UserCartCheckedOutItem::productId,
                        item -> item,
                        (left, right) -> new UserCartCheckedOutItem(
                                left.productId(),
                                left.amount() + right.amount(),
                                left.price()
                        )
                ));

        groupedItems.values().forEach(item ->
                order.addItem(
                        new ProductId(item.productId()),
                        item.amount(),
                        new Money(BigDecimal.valueOf(item.price()))
                )
        );

        Map<UUID, Integer> eventItems = groupedItems.values().stream()
                .collect(Collectors.toMap(
                        UserCartCheckedOutItem::productId,
                        UserCartCheckedOutItem::amount
                ));

        order.registerEvent(new OrderPlaced(order.id.value(), order.userId.value(), eventItems));

        return order;
    }

    public void addItem(ProductId productId, Integer amount, Money price) {
        var item = OrderItem.add(productId, amount, price);
        this.items.add(item);
    }

    public void handleReducedStock() {
        if (this.status != Status.PENDING) {
            return;
        }

        this.reducedItemsCount++;

        if (this.reducedItemsCount >= this.items.size()) {
            this.status = Status.COMPLETED;
            this.registerEvent(new OrderCompleted(this.id.value()));
        }
    }

    public void complete() {
        if (this.status != Status.PENDING) {
            return;
        }

        this.status = Status.COMPLETED;
        this.registerEvent(new OrderCompleted(this.id.value()));
    }

    public void cancel() {
        if (this.status != Status.PENDING) {
            return;
        }

        this.status = Status.CANCELLED;
        this.registerEvent(new OrderCancelled(this.id.value()));
    }

    public void fail() {
        if (this.status != Status.PENDING) {
            throw new IllegalStateException("Only pending orders can be failed");
        }

        this.status = Status.FAILED;
        this.registerEvent(new OrderFailed(this.id.value()));
    }
}