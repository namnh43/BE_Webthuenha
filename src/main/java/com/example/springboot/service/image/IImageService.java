package com.example.springboot.service.image;

import com.example.springboot.model.Image;
import com.example.springboot.service.IGeneralService;

public interface IImageService extends IGeneralService<Image> {
    Iterable<Image> getImageByHouse(Image image);
}
