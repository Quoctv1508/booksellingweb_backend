package vn.alaxed.booksellingweb_backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.alaxed.booksellingweb_backend.dao.BookRepository;
import vn.alaxed.booksellingweb_backend.dto.WishListDTO;
import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.User;
import vn.alaxed.booksellingweb_backend.entity.WishList;
import vn.alaxed.booksellingweb_backend.service.BookService;
import vn.alaxed.booksellingweb_backend.service.UserService;
import vn.alaxed.booksellingweb_backend.service.WishListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/wishlist")

public class WishListController {
    @Autowired
    WishListService wishListService;
    @Autowired
    UserService userService;
    @Autowired
    BookRepository bookRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addToWishList(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int bookId) {
        User user = userService.findByUsername(userDetails.getUsername());
        Optional<WishList> wl = wishListService.findByUserAndBook(user.getUserId(), bookId);
        if(wl.isPresent()){
            wishListService.removeFromWishList(user.getUserId(), bookId);
            WishListDTO wishList = new WishListDTO(wl.get());
            wishList.setBookId(0);
            return ResponseEntity.ok(wishList);
        }
        WishListDTO wListDTO = new WishListDTO(wishListService.addToWishList(user.getUserId(), bookId)); 

        return ResponseEntity.ok(wListDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getWishlist(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        List<WishList> wls = wishListService.getWishList(user.getUserId());
        List<WishListDTO> wListDTO =  wls.stream().map(w -> new WishListDTO(w)).toList();
       
        return ResponseEntity.ok(wListDTO);
    }
    

    @DeleteMapping("/del")
    public ResponseEntity<?> delWishList(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int bookId){
        User user = userService.findByUsername(userDetails.getUsername());
        
        wishListService.removeFromWishList(user.getUserId(), bookId);
        
        Map<String, Object> body = Map.of(
                "status", "ok",
                "updated", bookId,
                "message", "Delete successfully"
            );
        return ResponseEntity.ok(body);

    }
    
}
