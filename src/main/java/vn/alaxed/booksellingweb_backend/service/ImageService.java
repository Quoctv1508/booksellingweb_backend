package vn.alaxed.booksellingweb_backend.service;

import vn.alaxed.booksellingweb_backend.entity.Image;

public interface ImageService {

    void deleteImage(int id);

    Image addImage(int bookId, Image image);
} 