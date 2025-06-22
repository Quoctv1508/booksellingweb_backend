package vn.alaxed.booksellingweb_backend.entity;


import java.sql.Date;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "billing_address", length = 512)
    private String billingAddress;

    @Column(name = "shopping_address", length = 512)
    private String shoppingAddress;

    @Column(name = "payment_fee")
    private double paymentFee;

    @Column(name = "delivery_fee")
    private double deliveryFee;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "total_product_price")
    private double totalProductPrice;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @ManyToOne(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    } )
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    })
    private List<OrderDetail> listOrderDetails;

    @ManyToOne(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    } )
    @JoinColumn(name = "payment_method_id", nullable = true)
    private PaymentMethod paymentMethod;

    @ManyToOne(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    } )
    @JoinColumn(name = "delivery_method_id", nullable = true)
    private DeliveryMethod deliveryMethod;

    
}
