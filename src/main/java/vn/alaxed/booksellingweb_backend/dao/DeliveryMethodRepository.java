package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.alaxed.booksellingweb_backend.entity.DeliveryMethod;

@RepositoryRestResource(path = "delivery-method")
public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, Integer> {

}
