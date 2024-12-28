package com.ticketing.ticketing_backend.Service;
import com.ticketing.ticketing_backend.Model.Email;
import java.util.List;
import java.util.Optional;

public interface EmailService {
    String sendAndSaveEmail(String toEmail, String subject, String body);
    List<Email> getAllEmail();
    Optional<Email> getEmailById(String emailId);
    Email updateEmail(String emailId, Email emailDetails);
    void deleteEmail(String emailId);
    Email addEmail(Email email);
}
