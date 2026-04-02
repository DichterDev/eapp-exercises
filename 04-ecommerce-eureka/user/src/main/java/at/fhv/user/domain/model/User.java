package at.fhv.user.domain.model;

import java.util.*;
import java.util.stream.Collectors;

import at.fhv.common.domain.event.user.*;
import at.fhv.common.domain.model.BaseDomainRoot;
import at.fhv.common.domain.model.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends BaseDomainRoot {
    private final UserId id;
    private String name;

    @Builder.Default
    List<CartItem> cart = new ArrayList<>();

    public static User register(UserId id, String name) {
        var user = User.builder().id(id).name(name).build();

        user.registerEvent(new UserRegistered(user.getId().value(), user.getName()));

        return user;
    }

    public void rename(String newName) {
        String oldName = String.valueOf(this.name);

        this.name = newName;

        this.registerEvent(new UserChangedName(this.id.value(), oldName));
    }

    public void addCartItem(ProductId productId, Integer amount, Money price) {
        this.cart = new ArrayList<>(this.cart);
        this.cart.add(new CartItem(productId, amount, price));

        this.registerEvent(new UserCartAddedItem(this.id.value(), productId.value()));
    }

    public UUID checkout() {
        HashMap<UUID, Integer> items = new HashMap<>();

        this.cart.forEach(
            item -> items.merge(item.productId().value(), item.amount(), Integer::sum)
        );

        var event = new UserCartCheckedOut(this.id.value(), items);

        this.registerEvent(event);

        return event.id();
    }

    public void completeCheckout() {
        this.cart = new ArrayList<>();
    }
}
