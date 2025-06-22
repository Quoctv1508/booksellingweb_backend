package vn.alaxed.booksellingweb_backend.entity;

import jakarta.annotation.Generated;
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
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "sale_Price")
    private double salePrice;

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
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
