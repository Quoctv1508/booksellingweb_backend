package vn.alaxed.booksellingweb_backend.service.impl;

import java.util.Optional;
import java.util.function.BooleanSupplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.alaxed.booksellingweb_backend.dao.BookRepository;
import vn.alaxed.booksellingweb_backend.dao.ImageRepository;
import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.Image;
import vn.alaxed.booksellingweb_backend.service.ImageService;


@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    BookRepository bookRepository;


    @Override
    @Transactional
    public void deleteImage(int id) {
        Optional<Image> imageOpt = imageRepository.findById(id);
        if (imageOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy ảnh có ID = " + id);
        }

        imageRepository.delete(imageOpt.get());
    }

    @Override
    public Image addImage(int bookId, Image image) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Không tìm thấy sách: " + bookId));
        image.setBook(book);
        return imageRepository.save(image);

    }



}
