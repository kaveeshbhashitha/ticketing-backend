package com.ticketing.ticketing_backend.Controller;
import com.ticketing.ticketing_backend.Implementation.EmailServiceImplementation;
import com.ticketing.ticketing_backend.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"https://oficialticketing-frontend.netlify.app", "http://localhost:5173","https://ticketing-frontend-ruddy.vercel.app"})
@RequestMapping("/api/notification")
public class EmailController {
    @Autowired
    private EmailServiceImplementation emailServiceImplementation;

    @GetMapping("/getAllNotification")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Email> getAllEmail() {
        return emailServiceImplementation.getAllEmail();
    }

    @GetMapping("/getNotificationById/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Optional<Email> getEmailById(@PathVariable("id") String emailId) {
        return emailServiceImplementation.getEmailById(emailId);
    }
    @PostMapping("/addNotification")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Email addEmail(@RequestBody Email email) {
        return emailServiceImplementation.addEmail(email);
    }

    @PutMapping("/updateNotification/{id}")
    public Email updateEmail(@PathVariable("id") String emailId, @RequestBody Email email) {
        return emailServiceImplementation.updateEmail(emailId, email);
    }
    @DeleteMapping("/deleteNotification/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteEmail(@PathVariable("id") String emailId) {
        emailServiceImplementation.deleteEmail(emailId);
        return "Notification deleted with id " + emailId;
    }
}


