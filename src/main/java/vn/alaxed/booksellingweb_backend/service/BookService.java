package vn.alaxed.booksellingweb_backend.service;

import java.util.List;

import vn.alaxed.booksellingweb_backend.entity.Book;

public interface BookService {
    public List<Book> findAllBooks();

    Book addCategoryToBook(int bookId, List<Integer> categoryIds);

    Book updateCategoryFromBook(int bookId, List<Integer> categoryIds);
    
}