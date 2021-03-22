package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.dto.JwtAuthRequestDto;
import am.bigshopdemo.demo.dto.JwtAuthResponseDto;
import am.bigshopdemo.demo.model.Cart;
import am.bigshopdemo.demo.model.Orders;
import am.bigshopdemo.demo.model.User;
import am.bigshopdemo.demo.service.UserService;
import am.bigshopdemo.demo.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Value("${file.upload.dir}")
    private String uploadDir;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public void register(@RequestBody User user, Cart cart, Orders orders) {
        userService.register(user,cart,orders);
    }

    @GetMapping("/activate/{email},{token}")
    public void activate(@PathVariable("email") String email, @PathVariable("token") String token) {
        userService.activate(email,token);
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody JwtAuthRequestDto authRequestDto) {
        String email = authRequestDto.getEmail();
        User byEmail = userService.findByEmail(email);
        if (byEmail != null) {
            User user = byEmail;
            if (passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
                String token = jwtTokenUtil.generateToken(user.getEmail());
                JwtAuthResponseDto response = JwtAuthResponseDto.builder()
                        .token(token)
                        .user(user)
                        .build();
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

}
