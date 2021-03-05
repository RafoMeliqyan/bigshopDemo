package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Featuring;
import am.bigshopdemo.demo.model.Product;
import am.bigshopdemo.demo.model.Status;
import am.bigshopdemo.demo.repository.ProductRepository;
import am.bigshopdemo.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("/allProducts")
    public List<Product> allProducts() {
        return productService.findAll();
    }

    @GetMapping("/searchProducts/{name}")
    public List<Product> searchProducts(@PathVariable("name") String name) {
        return productService.findAllByNameContaining(name);
    }

    @GetMapping("/product/{id}")
    public Optional<Product> product(@PathVariable("id") int id) {
        return productService.findById(id);
    }

    @GetMapping("/newProducts/{status}")
    public List<Product> newProducts(@PathVariable("status") Status status) {
        return productService.findAllByStatus(status);
    }

    @GetMapping("/featuringProducts/{featuring}")
    public List<Product> featuringProducts(@PathVariable("featuring") Featuring featuring) {
        return productService.findAllByFeaturing(featuring);
    }

    //Filters
    @GetMapping("/filterByBrand/{brand}")
    public List<Product> productByBrand(@PathVariable("brand") String brand) {
        return productService.findAllByBrand(brand);
    }

    @GetMapping("/filterByCategory/{id}")
    public List<Product> products(@PathVariable("id") int id) {
        return productService.findAllByCategory_Id(id);
    }

    @GetMapping("/filterByPrice/{min}/{max}")
    public List<Product> filterByMinAndMax(@PathVariable("min") int min,@PathVariable("max") int max) {
        return productRepository.filterProductsByPrice(min, max);
    }

    @GetMapping("/filterByCategoryAndBrand/{categoryId},{brand}")
    public List<Product> filter(@PathVariable("categoryId") int categoryId,@PathVariable("brand") String brand) {
        return productRepository.findAllByCategoryIdAndBrand(categoryId, brand);
    }

    @GetMapping("/filterByPriceAndCategory/{min}/{max}/{categoryId}")
    public List<Product> filterByPriceAndCategory(@PathVariable("min") int min,
                                                  @PathVariable("max") int max,
                                                  @PathVariable("categoryId") int categoryId) {
        return productRepository.filterProductsByPriceAndCategoryId(min,max,categoryId);
    }

    @GetMapping("/filterByPriceAndBrand/{min}/{max}/{brand}")
    public List<Product> filterByPriceAndBrand(@PathVariable("min") int min,
                                               @PathVariable("max") int max,
                                               @PathVariable("brand") String brand) {
        return productRepository.filterProductsByPriceAndBrand(min,max,brand);
    }

    @GetMapping("/filterByPriceAndBrandAndCategory/{min}/{max}/{brand}/{categoryId}")
    public List<Product> filterByPriceAndBrandAndCategory(@PathVariable("min") int min,
                                                          @PathVariable("max") int max,
                                                          @PathVariable("brand") String brand,
                                                          @PathVariable("categoryId") int categoryId) {
        return productRepository.filterProductsByPriceAndBrandAndCategory(min,max,brand,categoryId);
    }

}
