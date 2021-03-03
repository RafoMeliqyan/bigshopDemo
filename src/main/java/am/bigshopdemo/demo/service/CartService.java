package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.Cart;
import am.bigshopdemo.demo.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart findCartByUserId(int userId) {
        return cartRepository.findCartByUserId(userId);
    }

    public Cart findCartById(int id) {
        return cartRepository.findCartById(id);
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
