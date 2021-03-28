package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.Image;
import am.bigshopdemo.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void save(Image image) {
        imageRepository.save(image);
    }

    public void deleteById(int id) {
        imageRepository.deleteById(id);
    }
}
