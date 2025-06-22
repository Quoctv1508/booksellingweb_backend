package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.alaxed.booksellingweb_backend.entity.WishList;

public interface WishListRepository extends JpaRepository<WishList, Integer> {

}
