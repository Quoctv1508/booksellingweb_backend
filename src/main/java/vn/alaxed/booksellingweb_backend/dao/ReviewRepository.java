package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.alaxed.booksellingweb_backend.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
