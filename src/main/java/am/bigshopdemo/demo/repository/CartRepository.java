package am.bigshopdemo.demo.repository;

import am.bigshopdemo.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findCartByUserId(Integer userId);
    Cart findCartById(Integer id);

    @Query(value = "DELETE FROM cart WHERE products.id =:productId",  nativeQuery = true)
    void deleteProducts(@Param("productId") Integer productId);

}
