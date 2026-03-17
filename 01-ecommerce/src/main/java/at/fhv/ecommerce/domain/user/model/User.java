package at.fhv.ecommerce.domain.user.model;

import java.util.ArrayList;
import java.util.List;
import at.fhv.ecommerce.domain.common.DomainRoot;
import at.fhv.ecommerce.domain.user.event.UserCartCheckedOutEvent;
import at.fhv.ecommerce.domain.user.event.UserCartCompletedCheckoutEvent;
import at.fhv.ecommerce.domain.user.event.UserRegisteredEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends DomainRoot {
    @Builder.Default
    private final UserId id = UserId.generate();
    private final String name;

    @Builder.Default
    private List<CartItem> cart = new ArrayList<>();

    public static User register(String name) {
        var user = User.builder().name(name).build();

        user.registerEvent(new UserRegisteredEvent(user));

        return user;
    }

    public void addCartItem(CartItem item) {
        this.cart.add(item);
    }

    public void checkoutCart() {
        this.registerEvent(new UserCartCheckedOutEvent(this));
    }

    public void completeCartCheckout() {
        this.cart = new ArrayList<>();

        this.registerEvent(new UserCartCompletedCheckoutEvent(this));
    }
}
