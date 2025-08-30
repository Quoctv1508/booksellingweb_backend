package vn.alaxed.booksellingweb_backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vn.alaxed.booksellingweb_backend.dao.UserRepository;
import vn.alaxed.booksellingweb_backend.entity.Message;
import vn.alaxed.booksellingweb_backend.entity.User;

@Service
public class AccountService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> registerUser(User user){
        //Kiểm tra tên đăn nhập đã tồn tại chưa
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body(new Message("Tên đăng nhập đã tồn tại"));
        }

        //Kiểm tra email đã tồn tại
        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(new Message("Email đã tồn tại"));
        }
        
        //Mẫ hóa mật khẩu
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);

        //Gán và gửi thông tin kích hoạt
        user.setActivationCode(createActiveCode());
        user.setActive(false);


        //Lưu user vào database
        User resgissUser = userRepository.save(user);

        sendEmailActive(user.getEmail(), user.getActivationCode());

        return ResponseEntity.ok("Đăng ký thành công");

    }

    private String createActiveCode(){
        //mã ngẫu nhiên
        return UUID.randomUUID().toString();
    }

    private void sendEmailActive(String email, String activeCode){
        String subject = "KÍCH HOẠT TÀI KHOẢN CỦA BẠN TẠI ALSTORE";
        String text = "Vui lòng sử dụng mã sau để kích hoạt cho tài khoản <"+ email +">:<html><body> <br/> <h1>"+ activeCode +"</h1> </body></html> ";
        String url = "http://localhost:3000/active/"+email+"/"+activeCode;
        text += "</br> <h1>Click vào link dưới đây để kích hoạt tài khoản: </h1> ";
        text += "</br> <a href="+ url +">"+ url+" </a> ";
        emailService.sendMessage("quoctran39252@gmail.com", email, subject, text);

    }

    //Kich hoạt tài khoản
    public ResponseEntity<?> activeAccount(String email, String activeCode){
        User user = userRepository.findByEmail(email);
        if(user == null){
            return ResponseEntity.badRequest().body(new Message("Người dùng không tồn tại"));
        }
        if(user.isActive()){
            return ResponseEntity.badRequest().body(new Message("Người dùng đã kích hoạt"));
        }
        if(activeCode.equals(user.getActivationCode())){
            user.setActive(true);
            userRepository.save(user);
            return ResponseEntity.ok("Kích hoạt tài khoản thành công");

        }else{
            return ResponseEntity.badRequest().body(new Message("Mã kích hoạt không chính xác"));
        }

    }
}
