package vn.alaxed.booksellingweb_backend.dto;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.alaxed.booksellingweb_backend.entity.DeliveryMethod;
import vn.alaxed.booksellingweb_backend.entity.Order;
import vn.alaxed.booksellingweb_backend.entity.PaymentMethod;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int orderId;

    private Date createdAt;

    private String billingAddress;

    private String shoppingAddress;

    private double paymentFee;

    private double deliveryFee;

    private double totalPrice;

    private double totalProductPrice;

    private String paymentStatus;

    private String deliveryStatus;

    private PaymentMethod paymentMethod;

    private DeliveryMethod deliveryMethod;

    public OrderDTO(Order order) {
        this.orderId = order.getOrderId();
        this.createdAt = order.getCreatedAt();
        this.billingAddress = order.getBillingAddress();
        this.shoppingAddress = order.getShoppingAddress();
        this.paymentFee = order.getPaymentFee();
        this.deliveryFee = order.getDeliveryFee();
        this.totalPrice = order.getTotalPrice();
        this.totalProductPrice = order.getTotalProductPrice();
        this.paymentStatus = order.getPaymentStatus();
        this.deliveryStatus = order.getDeliveryStatus();
        this.paymentMethod = order.getPaymentMethod();
        this.deliveryMethod = order.getDeliveryMethod();
    }

}
