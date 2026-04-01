package at.fhv.order.presentation;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.order.application.command.PlaceOrder;
import at.fhv.order.application.handler.OrderCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderWriteController {

    private final OrderCommandHandler command;

    @PostMapping("/place")
    public ResponseEntity<CommandResponse> place(@RequestBody PlaceOrder req) {
        var res = command.place(req);
        return ResponseEntity.status(201).body(res);
    }
}