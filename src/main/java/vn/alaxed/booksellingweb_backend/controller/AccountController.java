package vn.alaxed.booksellingweb_backend.controller;

import java.net.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.alaxed.booksellingweb_backend.dto.UserDTO;
import vn.alaxed.booksellingweb_backend.entity.User;
import vn.alaxed.booksellingweb_backend.security.JwtResponse;
import vn.alaxed.booksellingweb_backend.security.LoginRequest;
import vn.alaxed.booksellingweb_backend.service.AccountService;
import vn.alaxed.booksellingweb_backend.service.JwtService;
import vn.alaxed.booksellingweb_backend.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/account")

public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User user) {

        ResponseEntity<?> response = accountService.registerUser(user);
        return response;
    }

    @GetMapping("/active")
    public ResponseEntity<?> activeAccount(@RequestParam String email, @RequestParam String activeCode) {
        ResponseEntity<?> response = accountService.activeAccount(email, activeCode);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Xác thực ngừi dùng bằng tên đăng nhập và mật khẩu
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }

        } catch (AuthenticationException e) {
            // Xác thục không thành công
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không chính xác");
        }

        return ResponseEntity.badRequest().body("Xác thực không thành công");

    }

    @GetMapping("/me")
    public ResponseEntity<?> getMethodName(Authentication authentication) {
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(Authentication authentication, @RequestBody User user) {
        String username = authentication.getName();

         User existingUser = userService.findByUsername(username);
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại");
        }
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setGender(user.isGender());
        existingUser.setEmail(user.getEmail());
        existingUser.setNumberPhone(user.getNumberPhone());
        existingUser.setBillingAddress(user.getBillingAddress());
        existingUser.setShippingAddress(user.getShippingAddress());
        existingUser.setAvatar(user.getAvatar());
        User updatedUser = userService.updateProfile(existingUser);
        UserDTO userDTO = new UserDTO(updatedUser);
        return ResponseEntity.ok().body(userDTO);
    }

}
