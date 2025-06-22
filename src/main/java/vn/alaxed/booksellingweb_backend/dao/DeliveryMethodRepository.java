package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.alaxed.booksellingweb_backend.entity.DeliveryMethod;

public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, Integer> {

}
