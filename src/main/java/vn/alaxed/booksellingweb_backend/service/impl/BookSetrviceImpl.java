package vn.alaxed.booksellingweb_backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.alaxed.booksellingweb_backend.dao.BookRepository;
import vn.alaxed.booksellingweb_backend.dao.CategoryRepository;
import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.Category;
import vn.alaxed.booksellingweb_backend.service.BookService;

@Service
public class BookSetrviceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Book> findAllBooks() {
        List<Book> lBooks = bookRepository.findAll();
        return lBooks;
    }

    @Override
    public Book addCategoryToBook(int bookId, List<Integer> categoryId) {
       Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
       List<Category> categories = categoryRepository.findAllById(categoryId);
       for(Category category: categories){
            if(!book.getListCategories().contains(category)){
            book.getListCategories().add(category);
            category.getListBooks().add(book);
       }
       }
       return bookRepository.save(book);
    }

    @Override
    public Book updateCategoryFromBook(int bookId, List<Integer> categoryIds) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
       List<Category> categories = categoryRepository.findAllById(categoryIds);
        book.getListCategories().clear();
         for (Category category : categories) {
        book.getListCategories().add(category);
        category.getListBooks().add(book); // nếu bạn cần đồng bộ 2 chiều
    }
       return bookRepository.save(book);
    }

}
