package vn.alaxed.booksellingweb_backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rank")
    private float rank;

    @Column(name = "review")
    private String review;

    @ManyToOne(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    } )
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    } )
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
