package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.Featuring;
import am.bigshopdemo.demo.model.Product;
import am.bigshopdemo.demo.model.Status;
import am.bigshopdemo.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllByStatus(Status status) {
        return productRepository.findAllByStatus(status);
    }

    public List<Product> findAllByFeaturing(Featuring featuring) {
        return productRepository.findAllByFeaturing(featuring);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByBrand(String brand) {
        return productRepository.findAllByBrand(brand);
    }

    public List<Product> findAllByCategory_Id(int id) {
        return productRepository.findAllByCategory_Id(id);
    }

    public List<Product> getOneList(int productId) {
        return productRepository.findAllById(productId);
    }

    public Product getOne(int productId) {
        return productRepository.getOne(productId);
    }

    public List<Product> filterByBrandAndCategory(int id,String brand) {
        return productRepository.findAllByCategoryIdAndBrand(id,brand);
    }

    public List<Product> filterProductsByPrice(int min, int max) {
        return productRepository.filterProductsByPrice(min,max);
    }

    public List<Product> filterByPriceAndCategory(int min, int max, int id) {
        return productRepository.filterProductsByPriceAndCategoryId(min,max,id);
    }

    public List<Product> filterProductsByPriceAndBrand(int min, int max, String brand) {
        return productRepository.filterProductsByPriceAndBrand(min,max,brand);
    }

    public List<Product> filterProductsByPriceAndBrandAndCategory(int min, int max, String brand, int categoryId) {
        return productRepository.filterProductsByPriceAndBrandAndCategory(min,max,brand,categoryId);
    }

    public void updateProduct(Product product, int id) throws Exception {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product does not exist"));
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setAction(product.getAction());
        productToUpdate.setCount(product.getCount());
        productToUpdate.setStatus(product.getStatus());
        productToUpdate.setFeaturing(product.getFeaturing());
        productRepository.save(productToUpdate);
    }

}
