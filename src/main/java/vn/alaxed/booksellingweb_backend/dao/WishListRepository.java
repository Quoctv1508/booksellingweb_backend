package vn.alaxed.booksellingweb_backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.alaxed.booksellingweb_backend.entity.WishList;

@RepositoryRestResource(path = "wish-list")
public interface WishListRepository extends JpaRepository<WishList, Integer> {
    Optional<WishList> findByUser_UserIdAndBook_BookId(int userId, int bookId);
    List<WishList> findByUser_UserId(int userId); 
    void deleteByUser_UserIdAndBook_BookId(int userId, int bookId);

}   
