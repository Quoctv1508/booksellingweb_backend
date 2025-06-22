package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.alaxed.booksellingweb_backend.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
