package vn.alaxed.booksellingweb_backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.alaxed.booksellingweb_backend.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String lastName;
    private String firstName;
    private boolean gender;
    private String username;
    private String email;
    private String numberPhone;
    private String billingAddress;
    private String shippingAddress;
    private String avatar;

     public UserDTO(User user) {
        this.userId = user.getUserId();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.gender = user.isGender();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.numberPhone = user.getNumberPhone();
        this.billingAddress = user.getBillingAddress();
        this.shippingAddress = user.getShippingAddress();
        this.avatar = user.getAvatar();
    }
    
    
}
