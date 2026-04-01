package at.fhv.user.presentation;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.user.application.command.AddUserCartItem;
import at.fhv.user.application.command.CheckoutUserCart;
import at.fhv.user.application.command.RegisterUser;
import at.fhv.user.application.handler.UserCommandHandler;
import at.fhv.user.presentation.request.AddCartItemBody;
import at.fhv.user.presentation.request.RegisterUserBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserWriteController {
    private final UserCommandHandler command;

    @PostMapping("/register")
    public ResponseEntity<CommandResponse> register(@RequestBody RegisterUserBody body) {
        var res = command.register(new RegisterUser(
                body.userId(),
                body.name()
        ));
        return ResponseEntity.status(201).body(res);
    }

    @PostMapping("/{id}/cart/add")
    public void addCartItem(@PathVariable UUID id, @RequestBody AddCartItemBody body) {
        command.addItem(new AddUserCartItem(
                id,
                body.productId(),
                body.amount()
        ));
    }

    @PostMapping("/{id}/cart/checkout")
    public ResponseEntity<Void> checkout(@PathVariable UUID id) {
        command.checkout(new CheckoutUserCart(id));
        return ResponseEntity.accepted().build();
    }


}