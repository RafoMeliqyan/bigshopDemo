package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Orders;
import am.bigshopdemo.demo.model.Product;
import am.bigshopdemo.demo.repository.ProductRepository;
import am.bigshopdemo.demo.service.OrdersService;
import am.bigshopdemo.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("/addOrder")
    public void addOrder(@RequestBody Orders orders) {
        ordersService.save(orders);
    }

    @GetMapping("/ordersByUserId/{userId}")
    public Orders orders(@PathVariable("userId") int userId) {
        return ordersService.findByUserId(userId);
    }

    @GetMapping("/ordersById/{id}")
    public Orders ordersById(@PathVariable("id") int id) {
        return ordersService.findOrdersById(id);
    }

    @PutMapping("/buy/{userId}/{productId}")
    public void buy(@PathVariable("userId") int userId,
                    @PathVariable("productId") int productId) {
        Orders byUserId = ordersService.findByUserId(userId);
        List<Product> one = productService.getOne(productId);
        Product one1 = productRepository.getOne(productId);
        byUserId.setProducts(one);
        ordersService.save(byUserId);
        one1.setCount(one1.getCount()-1);
        productService.save(one1);
    }

}
