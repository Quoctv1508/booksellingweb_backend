package vn.alaxed.booksellingweb_backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.alaxed.booksellingweb_backend.entity.Order;
import vn.alaxed.booksellingweb_backend.entity.OrderDetail;
import vn.alaxed.booksellingweb_backend.entity.User;

@RepositoryRestResource(path = "orders")
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByUserAndPaymentStatus(User user, String status);
    
}
