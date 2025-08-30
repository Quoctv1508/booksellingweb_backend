package vn.alaxed.booksellingweb_backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.Order;
import vn.alaxed.booksellingweb_backend.entity.OrderDetail;
import vn.alaxed.booksellingweb_backend.entity.User;

@RepositoryRestResource(path = "order-detail")
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    Optional<OrderDetail> findByOrderAndBook(Order order, Book book);
    
    List<OrderDetail> findAllByOrder(Order order);
}
