package com.example.springboot.service.image;

import com.example.springboot.model.Image;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements IImageService{
    @Override
    public Iterable<Image> findAll() {
        return null;
    }

      public Optional<Image> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Image save(Image image) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<Image> getImageByHouse(Image image) {
        return null;
    }
}
