package com.ticketing.ticketing_backend.Implementation;
import com.ticketing.ticketing_backend.Model.Email;
import com.ticketing.ticketing_backend.Repository.EmailRepository;
import com.ticketing.ticketing_backend.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImplementation implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailRepository emailRepository;
    public String sendAndSaveEmail(String toEmail, String subject, String body) {
        Email emailRecord = new Email();
        try {
            // Send email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("pharmaforjob@gmail.com");
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);

            // Set email details for saving
            emailRecord.setToEmail(toEmail);
            emailRecord.setSubject(subject);
            emailRecord.setBody(body);
            emailRecord.setStatus("SENT");

            // Save email record
            emailRepository.save(emailRecord);
            return "Email sent successfully.";

        } catch (Exception e) {
            emailRecord.setToEmail(toEmail);
            emailRecord.setSubject(subject);
            emailRecord.setBody(body);
            emailRecord.setStatus("FAILED");

            emailRepository.save(emailRecord);
            return "Failed to send email.";
        }
    }
    public List<Email> getAllEmail() {
        return emailRepository.findAll();
    }
    @Override
    public Optional<Email> getEmailById(String emailId) {
        return emailRepository.findById(emailId);
    }
    @Override
    public Email updateEmail(String id, Email updateEmail) {
        Email existingEmail = getEmailById(id).orElseThrow(() -> new RuntimeException("Email not found with ID: " + id));
        updateEmail.setEmailId(existingEmail.getEmailId());
        emailRepository.save(updateEmail);
        return existingEmail;
    }
    @Override
    public void deleteEmail(String emailId) {
        emailRepository.deleteById(emailId);
    }
    @Override
    public Email addEmail(Email email) {
        return emailRepository.save(email);
    }
}
