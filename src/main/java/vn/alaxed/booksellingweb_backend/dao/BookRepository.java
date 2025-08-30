package vn.alaxed.booksellingweb_backend.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.Category;


@RepositoryRestResource(path = "books")
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    Page<Book> findByListCategories_CategoryId(@RequestParam("categoryId") int categoryId, Pageable pageable);

    Page<Book>findByTitleContainingAndListCategories_CategoryId(@RequestParam("title") String title, @RequestParam("categoryId") int categoryId, Pageable pageable);
    
    List<Book> findByListCategories(List<Category> listCategories);
    List<Book> findAll();
}
