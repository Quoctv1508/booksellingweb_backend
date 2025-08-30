package vn.alaxed.booksellingweb_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.alaxed.booksellingweb_backend.entity.Role;
import java.util.List;


@RepositoryRestResource(path = "roles")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByTitle(String title);

}
