package vn.alaxed.booksellingweb_backend.entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name= "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private int bookId;

    @Column(name="title", length = 256)
    private String title;
    
    @Column(name="author", length = 512)
    private String author;

    @Column(name="isbn", length = 256)
    private String ISBN;

    @Column(name="listed_price")
    private double listedPrice;

    @Column(name="sale_price")
    private double salePrice;

    @Column(name="description", columnDefinition = "text")
    private String description;

    @Column(name="quantity")
    private int quantity;

    @Column(name="avg_rank", length = 256)
    private Double avgRank;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
        name = "book_category",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> listCategories;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    })
    private List<Image> listImages;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    })
    private List<Review> listReviews;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<OrderDetail> listOrderDetails;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    })
    private List<WishList> listWishLists;
}
