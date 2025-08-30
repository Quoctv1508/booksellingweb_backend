package vn.alaxed.booksellingweb_backend.service.impl;

import java.text.Collator;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.alaxed.booksellingweb_backend.dao.RoleRepository;
import vn.alaxed.booksellingweb_backend.dao.UserRepository;
import vn.alaxed.booksellingweb_backend.entity.Role;
import vn.alaxed.booksellingweb_backend.entity.User;
import vn.alaxed.booksellingweb_backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
 
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList());
    }
     
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                rolesToAuthorities(user.getListRoles())
        );
        return userDetails;
    }


    @Override
    public User updateProfile(User user) {
        User u = userRepository.save(user);
        return u;
    }



}
