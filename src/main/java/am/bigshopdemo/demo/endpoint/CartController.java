package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.security.CurrentUser;
import am.bigshopdemo.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PutMapping("/addToCart/{productId}")
    public void addToCart(@AuthenticationPrincipal CurrentUser currentUser,
                          @PathVariable("productId") int productId) {
        cartService.addToCart(currentUser.getUser().getId(),productId);
    }

}
