package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.*;
import am.bigshopdemo.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final CartService cartService;
    private final OrdersService ordersService;
    private final ProductService productService;
    private final AddressService addressService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @GetMapping("/delete/user/{id}")
    public void deleteById(@PathVariable("id") int id) {
        userService.deleteById(id);
    }

    @PostMapping("/add/cart")
    public void addCart(@RequestBody Cart cart) {
        cartService.save(cart);
    }

    @PostMapping("/add/order")
    public void addOrder(@RequestBody Orders orders) {
        ordersService.save(orders);
    }

    @PostMapping("/add/product")
    public void addProducts(@RequestBody Product product, @PathVariable("image") MultipartFile file) {
        productService.save(product);
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable("id") int id) {
        productService.deleteById(id);
    }

    @PostMapping("/product/update/{id}")
    public void updateProduct(@PathVariable("id") int id, @RequestBody Product product) throws Exception {
        productService.updateProduct(product,id);
    }

    @GetMapping("/cart/{id}")
    public Cart getCart(@PathVariable("id") int id) {
        return cartService.findCartById(id);
    }

    @GetMapping("/address/{userId}")
    public Address addresses(@PathVariable("userId") int userId) {
        return addressService.findByUserId(userId);
    }

    @PostMapping("/category/add")
    public void addCategory(@RequestBody Category category) {
        categoryService.save(category);
    }

    @DeleteMapping("/image/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        imageService.deleteById(id);
    }

    @GetMapping("/ordersByUserId/{userId}")
    public Orders orders(@PathVariable("userId") int userId) {
        return ordersService.findByUserId(userId);
    }

    @GetMapping("/allUsers")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> userById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

}
