package am.bigshopdemo.demo.repository;

import am.bigshopdemo.demo.model.Featuring;
import am.bigshopdemo.demo.model.Product;
import am.bigshopdemo.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByNameContaining(String name);
    List<Product> findAllByStatus(Status status);
    List<Product> findAllByFeaturing(Featuring featuring);
    List<Product> findAllById(Integer id);

    //Filters
    List<Product> findAllByBrand(String brand);
    List<Product> findAllByCategory_Id(Integer id);

    List<Product> findAllByCategoryIdAndBrand(Integer categoryId, String brand);

    @Query(value = "SELECT * FROM product WHERE price >=:min and price <=:max", nativeQuery = true)
    List<Product> filterProductsByPrice(@Param("min") Integer min, @Param("max") Integer max);

    @Query(value = "SELECT * FROM product WHERE price >=:min and price <=:max and category_id = :categoryId", nativeQuery = true)
    List<Product> filterProductsByPriceAndCategoryId(@Param("min") Integer min, @Param("max") Integer max, @Param("categoryId") Integer categoryId);

    @Query(value = "SELECT * FROM product WHERE price >=:min and price <=:max and brand = :brand", nativeQuery = true)
    List<Product> filterProductsByPriceAndBrand(@Param("min") Integer min, @Param("max") Integer max, @Param("brand") String brand);

    @Query(value = "SELECT * FROM product WHERE price >=:min and price <=:max and brand = :brand and category_id = :categoryId", nativeQuery = true)
    List<Product> filterProductsByPriceAndBrandAndCategory(@Param("min") Integer min, @Param("max") Integer max, @Param("brand") String brand, @Param("categoryId") Integer categoryId);

}
