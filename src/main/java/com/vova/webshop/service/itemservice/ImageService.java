package com.vova.webshop.service.itemservice;

import com.vova.webshop.model.itemmodel.FileBucket;
import com.vova.webshop.model.itemmodel.Item;

import java.io.File;
import java.io.IOException;

public interface ImageService {
    void saveUploadedImage(FileBucket fileBucket, Item item) throws IOException;

    boolean saveLinkedImage(String link, Item item);

    void deleteImage(Integer id);

    File getImageByItemId(Integer id);

    String getImagePathByItemId(Integer id);
}