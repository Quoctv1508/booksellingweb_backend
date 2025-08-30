package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import vn.alaxed.booksellingweb_backend.entity.Category;
import java.util.List;
import vn.alaxed.booksellingweb_backend.entity.Book;



@RepositoryRestResource(path = "categories")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByListBooks_BookId(@RequestParam int bookId);
}
