package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Category;
import am.bigshopdemo.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @PostMapping("/category/add")
    public void addCategory(@RequestBody Category category) {
        categoryService.save(category);
    }

}
