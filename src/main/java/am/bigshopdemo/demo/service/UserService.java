package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.Cart;
import am.bigshopdemo.demo.model.Orders;
import am.bigshopdemo.demo.model.User;
import am.bigshopdemo.demo.repository.CartRepository;
import am.bigshopdemo.demo.repository.OrdersRepository;
import am.bigshopdemo.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrdersRepository ordersRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User getOne(int id) {
        return userRepository.getOne(id);
    }

    public void register(User user, Cart cart, Orders orders) {
        User byUsername = userRepository.findByEmail(user.getEmail());
        if (byUsername == null) {
            return;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        String profilePic = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        File image = new File(uploadDir, profilePic);
//        file.transferTo(image);
//        user.setImage(profilePic);
        user.setActive(false);
        user.setToken(UUID.randomUUID().toString());
        userRepository.save(user);
        cart.setUser(user);
        cart.setSum(0);
        cartRepository.save(cart);
        orders.setUser(user);
        orders.setSum(0);
        ordersRepository.save(orders);
        String link = "http://localhost:8080/activate?email=" + user.getEmail() + "&token=" + user.getToken();
        emailService.send(user.getEmail(),
                "Welcome", "Dear " + user.getName() + " You have successfully registered. Please activate your account by clicking on: " + link);
    }

    public void activate(String email, String token) {
        User byEmail = userRepository.findByEmail(email);
        if (byEmail != null) {
            User user = byEmail;
            if (user.getToken().equals(token)) {
                user.setActive(true);
                user.setToken("");
                userRepository.save(user);
            }
        }
    }

}
