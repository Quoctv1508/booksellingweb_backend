package vn.alaxed.booksellingweb_backend.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.Order;
import vn.alaxed.booksellingweb_backend.entity.OrderDetail;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private long id;

    private int quantity;

    private double salePrice;

    private int orderId;

    private int bookId;
    private String bookTitle;
    private String bookImage;
    private int bookQty;

    public OrderDetailDTO(OrderDetail orderDetail) {
        id = orderDetail.getId();
        quantity = orderDetail.getQuantity();
        salePrice = orderDetail.getSalePrice();
        orderId = orderDetail.getOrder().getOrderId();
        bookId = orderDetail.getBook().getBookId();
        bookTitle = orderDetail.getBook().getTitle();
        if (orderDetail.getBook().getListImages() != null
                && !orderDetail.getBook().getListImages().isEmpty()
                && orderDetail.getBook().getListImages().get(0).getData() != null) {
            bookImage = orderDetail.getBook().getListImages().get(0).getData();
        } else {
            bookImage = ""; 
        }

        bookQty = orderDetail.getBook().getQuantity();
    };

}
