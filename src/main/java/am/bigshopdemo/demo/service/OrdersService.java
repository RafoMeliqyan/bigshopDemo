package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.Orders;
import am.bigshopdemo.demo.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public void save(Orders orders) {
        ordersRepository.save(orders);
    }

    public Orders findByUserId(int userId) {
        return ordersRepository.findByUserId(userId);
    }

    public Orders findOrdersById(int id) {
        return ordersRepository.findOrdersById(id);
    }
}
