package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.Orders;
import am.bigshopdemo.demo.model.Product;
import am.bigshopdemo.demo.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ProductService productService;

    public void save(Orders orders) {
        ordersRepository.save(orders);
    }

    public Orders findByUserId(int userId) {
        return ordersRepository.findByUserId(userId);
    }

    public Orders findOrdersById(int id) {
        return ordersRepository.findOrdersById(id);
    }

    public void buy(int userId, int productId) {
        Orders byUserId = ordersRepository.findByUserId(userId);
        List<Product> one = productService.getOneList(productId);
        Product one1 = productService.getOne(productId);
        byUserId.setProducts(one);
        ordersRepository.save(byUserId);
        one1.setCount(one1.getCount()-1);
        productService.save(one1);
    }

}
