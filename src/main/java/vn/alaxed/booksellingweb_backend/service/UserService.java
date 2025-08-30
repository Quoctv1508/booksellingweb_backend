package vn.alaxed.booksellingweb_backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import vn.alaxed.booksellingweb_backend.entity.User;

public interface UserService extends UserDetailsService {
    public User findByUsername (String username);
    User updateProfile(User user);
}
