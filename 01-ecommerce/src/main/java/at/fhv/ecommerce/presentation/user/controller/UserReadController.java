package at.fhv.ecommerce.presentation.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.application.user.handler.UserQueryHandler;
import at.fhv.ecommerce.application.user.query.GetUserByIdQuery;
import at.fhv.ecommerce.application.user.query.GetUserByIdWithCartQuery;
import at.fhv.ecommerce.application.user.query.GetUserOrdersById;
import at.fhv.ecommerce.application.user.view.UserDetailView;
import at.fhv.ecommerce.application.user.view.UserView;
import at.fhv.ecommerce.presentation.user.mapper.UserResponseMapper;
import at.fhv.ecommerce.presentation.user.response.UserDetailResponse;
import at.fhv.ecommerce.presentation.user.response.UserOrdersResponse;
import at.fhv.ecommerce.presentation.user.response.UserResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserReadController {
    private final UserQueryHandler query;
    private final UserResponseMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {

        UserView view = query.handleGet(new GetUserByIdQuery(id));

        return ResponseEntity.ok(mapper.toResponse(view));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<UserDetailResponse> getDetail(@PathVariable UUID id) {
        UserDetailView view = query.handleGetWithCart(new GetUserByIdWithCartQuery(id));

        return ResponseEntity.ok(mapper.toResponse(view));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<UserOrdersResponse> getOrders(
        @PathVariable UUID id,
        @PathParam("page") Integer page,
        @PathParam("size") Integer size) {
        var orders = query.handleGetOrders(new GetUserOrdersById(id, page, size))
            .stream()
            .map(mapper::toResponse)
            .toList();

        return ResponseEntity.ok(new UserOrdersResponse(orders));
    }

}
