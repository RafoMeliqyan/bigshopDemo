package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Cart;
import am.bigshopdemo.demo.model.Product;
import am.bigshopdemo.demo.model.User;
import am.bigshopdemo.demo.repository.CartRepository;
import am.bigshopdemo.demo.service.CartService;
import am.bigshopdemo.demo.service.ProductService;
import am.bigshopdemo.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/addCart")
    public void addCart(@RequestBody Cart cart) {
        cartService.save(cart);
    }

    @GetMapping("/cart/{id}")
    public Cart getCart(@PathVariable("id") int id) {
        return cartService.findCartById(id);
    }

    //todo
    //CurrentUser
    @PutMapping("/addToCart/{userId}/{productId}")
    public void addToCart(@PathVariable("userId") int userId,
                          @PathVariable("productId") int productId) {
        User one = userService.getOne(userId);
        List<Product> one1 = productService.getOne(productId);
        Cart cartByUserId = cartService.findCartByUserId(userId);
        cartByUserId.setUser(one);
        cartByUserId.setProducts(one1);
        cartService.save(cartByUserId);
    }

}

