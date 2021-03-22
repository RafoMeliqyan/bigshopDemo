package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.Cart;
import am.bigshopdemo.demo.model.Product;
import am.bigshopdemo.demo.model.User;
import am.bigshopdemo.demo.repository.CartRepository;
import am.bigshopdemo.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public Cart findCartByUserId(int userId) {
        return cartRepository.findCartByUserId(userId);
    }

    public Cart findCartById(int id) {
        return cartRepository.findCartById(id);
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public void addToCart(int userId, int productId) {
        User one = userRepository.getOne(userId);
        List<Product> one1 = productService.getOneList(productId);
        Cart cartByUserId = cartRepository.findCartByUserId(userId);
        cartByUserId.setUser(one);
        cartByUserId.setProducts(one1);
        cartRepository.save(cartByUserId);
    }

}
