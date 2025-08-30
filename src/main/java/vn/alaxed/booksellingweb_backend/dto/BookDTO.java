package vn.alaxed.booksellingweb_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.alaxed.booksellingweb_backend.entity.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private int bookId;
    private String title;
    private String author;
    private String ISBN;
    private double listedPrice;
    private double salePrice;
    private String description;
    private int quantity;
    private Double avgRank;
    public BookDTO(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        ISBN = book.getISBN();
        this.listedPrice = book.getListedPrice();
        this.salePrice = book.getSalePrice();
        this.description = book.getDescription();
        this.quantity = book.getQuantity();
        this.avgRank = book.getAvgRank();
    }

    


}
