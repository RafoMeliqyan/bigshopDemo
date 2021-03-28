package am.bigshopdemo.demo.repository;

import am.bigshopdemo.demo.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Orders findByUserId(Integer userId);
    Orders findOrdersById(Integer id);

}
