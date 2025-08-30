package vn.alaxed.booksellingweb_backend.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.Image;
import vn.alaxed.booksellingweb_backend.entity.WishList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDTO {
    private int id;
    private int userId;
    private int bookId;
    private String bookTitle;
    private String author;
    private String bookImage;
    private Double price;

    public WishListDTO(WishList wl) {
        this.id = wl.getId();
        this.userId = wl.getUser().getUserId();
        this.bookId = wl.getBook().getBookId();
        this.bookTitle = wl.getBook().getTitle();
        this.author = wl.getBook().getAuthor();
        this.bookImage = wl.getBook().getListImages().stream()
                .findFirst()
                .map(Image::getData)
                .orElse("default.png");

        this.price = wl.getBook().getSalePrice();
    }

}
