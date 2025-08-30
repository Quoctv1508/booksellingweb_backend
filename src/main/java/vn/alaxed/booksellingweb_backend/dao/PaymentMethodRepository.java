package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.alaxed.booksellingweb_backend.entity.PaymentMethod;

@RepositoryRestResource(path = "payment-method")
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{

}
