package com.BankApplication.BankApplication.BankServiceimpl;

import com.BankApplication.BankApplication.BankService.EmailService;
import com.BankApplication.BankApplication.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;
    @Override
    public void sendEmailAlert(EmailDetails emailDetails) {
       try{
           SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
           simpleMailMessage.setFrom(senderEmail);
           simpleMailMessage.setTo(emailDetails.getRecipient());
           simpleMailMessage.setText(emailDetails.getMessageBody());
           simpleMailMessage.setSubject(emailDetails.getSubject());

     javaMailSender.send(simpleMailMessage);
           System.out.println("Mail sent Succesfully");
       }
       catch(Exception e){
           throw new RuntimeException(e);
       }
    }
}
