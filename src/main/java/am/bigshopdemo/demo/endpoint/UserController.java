package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Cart;
import am.bigshopdemo.demo.model.Orders;
import am.bigshopdemo.demo.model.User;
import am.bigshopdemo.demo.service.CartService;
import am.bigshopdemo.demo.service.EmailService;
import am.bigshopdemo.demo.service.OrdersService;
import am.bigshopdemo.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final CartService cartService;
    private final OrdersService ordersService;

    @PostMapping("/register")
    public void register(@RequestBody User user, Cart cart, Orders orders) {
        Optional<User> byUsername = userService.findByEmail(user.getEmail());
        if (byUsername.isPresent()) {
            return;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        String profilePic = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        File image = new File(uploadDir, profilePic);
//        file.transferTo(image);
//        user.setImage(profilePic);
        user.setActive(false);
        user.setToken(UUID.randomUUID().toString());
        userService.save(user);
        cart.setUser(user);
        cart.setSum(0);
        cartService.save(cart);
        orders.setUser(user);
        orders.setSum(0);
        ordersService.save(orders);
        String link = "http://localhost:8080/activate?email=" + user.getEmail() + "&token=" + user.getToken();
        emailService.send(user.getEmail(),
                "Welcome", "Dear " + user.getName() + " You have successfully registered. Please activate your account by clicking on: " + link);
    }

    @GetMapping("/activate/{email},{token}")
    public void activate(@PathVariable("email") String email, @PathVariable("token") String token) {
        Optional<User> byEmail = userService.findByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (user.getToken().equals(token)) {
                user.setActive(true);
                user.setToken("");
                userService.save(user);
            }
        }
    }

    @GetMapping("/allUsers")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> userById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @GetMapping("/delete/user/{id}")
    public void deleteById(@PathVariable("id") int id) {
        userService.deleteById(id);
    }

}
