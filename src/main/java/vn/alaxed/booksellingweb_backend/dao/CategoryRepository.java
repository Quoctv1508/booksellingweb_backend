package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.alaxed.booksellingweb_backend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
