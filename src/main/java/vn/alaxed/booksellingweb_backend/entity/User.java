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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firtsName;

    @Column(name = "gender")
    private char gender;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password", length = 512)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<Review> listReviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    })
    private List<WishList> listWishLists;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> listRoles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    })
    private List<Order> listOrders;

}
