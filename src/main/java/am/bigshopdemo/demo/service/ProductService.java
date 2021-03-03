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

    public List<Product> getOne(int productId) {
        return productRepository.findAllById(productId);
    }
}
