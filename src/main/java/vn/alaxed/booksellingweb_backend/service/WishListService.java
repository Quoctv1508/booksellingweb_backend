package vn.alaxed.booksellingweb_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.alaxed.booksellingweb_backend.entity.WishList;

public interface WishListService {

    public WishList addToWishList(int userId, int bookId);

    public Optional<WishList> findByUserAndBook(int userId, int bookId);

    public List<WishList> getWishList(int userId);

    public void removeFromWishList(int userId, int bookId);


}
