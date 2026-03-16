package at.fhv.ecommerce.presentation.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.application.user.command.AddItemToUserCartCommand;
import at.fhv.ecommerce.application.user.command.RegisterUserCommand;
import at.fhv.ecommerce.application.user.handler.UserCommandHandler;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.model.UserId;
import at.fhv.ecommerce.presentation.user.mapper.UserResponseMapper;
import at.fhv.ecommerce.presentation.user.request.AddItemToUserCartRequest;
import at.fhv.ecommerce.presentation.user.request.CreateUserRequest;
import at.fhv.ecommerce.presentation.user.response.UserIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserWriteController {
    private final UserCommandHandler command;
    private final UserResponseMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<UserIdResponse> create(@RequestBody CreateUserRequest req) {
        var id = command.handleRegister(new RegisterUserCommand(req.name()));

        return ResponseEntity.ok(mapper.toResponse(id));
    }

    @PostMapping("/cart/add")
    public ResponseEntity<?> cartAdd(@RequestBody AddItemToUserCartRequest req) {
        command.handleAddItem(
            new AddItemToUserCartCommand(
                new UserId(req.userId()),
                new ProductId(req.productId()),
                req.amount()
            )
        );

        return ResponseEntity.ok().build();
    }
}
