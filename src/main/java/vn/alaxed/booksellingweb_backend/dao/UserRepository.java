package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.alaxed.booksellingweb_backend.entity.User;
import java.util.List;


@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    public User findByUsername(String username);

    public User findByEmail(String email);


}
