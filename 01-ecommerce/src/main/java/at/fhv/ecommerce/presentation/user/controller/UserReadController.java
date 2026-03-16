package at.fhv.ecommerce.presentation.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.application.user.handler.UserQueryHandler;
import at.fhv.ecommerce.application.user.query.GetUserByIdQuery;
import at.fhv.ecommerce.application.user.query.GetUserByIdWithCartQuery;
import at.fhv.ecommerce.application.user.view.UserDetailView;
import at.fhv.ecommerce.application.user.view.UserView;
import at.fhv.ecommerce.presentation.user.mapper.UserResponseMapper;
import at.fhv.ecommerce.presentation.user.response.UserDetailResponse;
import at.fhv.ecommerce.presentation.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserReadController {
    private final UserQueryHandler query;
    private final UserResponseMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@RequestParam String id) {
        UUID uuid = UUID.fromString(id);

        UserView view = query.handleGet(new GetUserByIdQuery(uuid));

        return ResponseEntity.ok(mapper.toResponse(view));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<UserDetailResponse> getDetail(@RequestParam String id) {
        UUID uuid = UUID.fromString(id);

        UserDetailView view = query.handleGetWithCart(new GetUserByIdWithCartQuery(uuid));

        return ResponseEntity.ok(mapper.toResponse(view));
    }
}
