package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Image;
import am.bigshopdemo.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/add/image")
    public void addImage(@RequestBody Image image) {
        imageService.save(image);
    }

}
