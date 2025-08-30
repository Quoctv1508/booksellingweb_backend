package vn.alaxed.booksellingweb_backend.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.alaxed.booksellingweb_backend.dao.BookRepository;
import vn.alaxed.booksellingweb_backend.dto.BookDTO;
import vn.alaxed.booksellingweb_backend.dto.UserDTO;
import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.User;
import vn.alaxed.booksellingweb_backend.service.BookService;
import vn.alaxed.booksellingweb_backend.service.impl.BookSetrviceImpl;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllBooks() {
        List<Book> listBook = bookService.findAllBooks();
        List<BookDTO> bookDTO = listBook.stream().map(book -> new BookDTO(book)).collect(Collectors.toList());
        

        return ResponseEntity.ok().body(bookDTO);
    }

    @PostMapping("/{bookId}/categories")
    public ResponseEntity<?> addCategoryToBook(@PathVariable int bookId, @RequestBody List<Integer> categoryIds) {
        Book updateBook = bookService.addCategoryToBook(bookId, categoryIds);
        BookDTO bookDTO = new BookDTO(updateBook);
        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("update/{bookId}/categories")
    public ResponseEntity<?> delCategoryFromBook(@PathVariable int bookId, @RequestBody List<Integer> categoryIds){
        Book updateBook = bookService.updateCategoryFromBook(bookId, categoryIds);
        BookDTO bookDTO = new BookDTO(updateBook);
        return ResponseEntity.ok(bookDTO);

    }
    
    

}
