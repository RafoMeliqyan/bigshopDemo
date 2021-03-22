package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Orders;
import am.bigshopdemo.demo.security.CurrentUser;
import am.bigshopdemo.demo.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/ordersById/{id}")
    public Orders ordersById(@PathVariable("id") int id) {
        return ordersService.findOrdersById(id);
    }

    //CurrentUser
    @PutMapping("/buy/{productId}")
    public void buy(@AuthenticationPrincipal CurrentUser currentUser,
                    @PathVariable("productId") int productId) {
        ordersService.buy(currentUser.getUser().getId(),productId);
    }

}
