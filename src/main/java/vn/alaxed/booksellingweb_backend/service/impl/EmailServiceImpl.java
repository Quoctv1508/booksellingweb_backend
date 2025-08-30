package vn.alaxed.booksellingweb_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import vn.alaxed.booksellingweb_backend.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender emailSender;

    
    
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }



    @Override
    public void sendMessage(String from, String to, String subject, String text) {
        //MimeMailMessage -> có gửi đính kèm
        //SimpleMailMessage -> gửi nội dung thông thường

        MimeMessage message = emailSender.createMimeMessage();
       
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);
        } catch (Exception e) {
            // TODO: handle exception
        }
        
     
        //Thực hiện hành động gửi email
        emailSender.send(message);
    }

}
