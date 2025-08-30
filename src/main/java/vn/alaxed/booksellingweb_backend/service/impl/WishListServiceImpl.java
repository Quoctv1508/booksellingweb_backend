package vn.alaxed.booksellingweb_backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.alaxed.booksellingweb_backend.dao.BookRepository;
import vn.alaxed.booksellingweb_backend.dao.UserRepository;
import vn.alaxed.booksellingweb_backend.dao.WishListRepository;
import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.User;
import vn.alaxed.booksellingweb_backend.entity.WishList;
import vn.alaxed.booksellingweb_backend.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    WishListRepository wlrepo;

    @Override
    public WishList addToWishList(int userId, int bookId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found!"));
        WishList wishList = new WishList();
        wishList.setBook(book);
        wishList.setUser(user);
        return wlrepo.save(wishList);

    }

    @Transactional
    @Override
    public void removeFromWishList(int userId, int bookId) {
        WishList wl = wlrepo.findByUser_UserIdAndBook_BookId(userId, bookId)
                .orElseThrow(() -> new RuntimeException("Wishlist item not found"));
        wlrepo.delete(wl);

    }

    @Override
    public List<WishList> getWishList(int userId) {
        return wlrepo.findByUser_UserId(userId);
    }

    @Override
    public Optional<WishList> findByUserAndBook(int userId, int bookId) {
       return wlrepo.findByUser_UserIdAndBook_BookId(userId, bookId);
    }

}
