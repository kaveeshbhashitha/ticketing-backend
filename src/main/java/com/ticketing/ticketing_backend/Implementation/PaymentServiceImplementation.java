package com.ticketing.ticketing_backend.Implementation;
import com.ticketing.ticketing_backend.Model.*;
import com.ticketing.ticketing_backend.Repository.*;
import com.ticketing.ticketing_backend.Service.PaymentService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImplementation implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private EventRepository generalEventRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private JavaMailSender mailSender;

    public PaymentServiceImplementation(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    public Payment processPayment(Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);

        if (payment.isCheckAccept()) {
            sendPaymentSuccessEmail(payment);
        }
        return savedPayment;
    }
    public void sendPaymentSuccessEmail(Payment payment) {
        // Fetch user details by userId
        Optional<User> userOptional = userRepository.findByUserId(payment.getUserId());
        Optional<Reservation> reservationOptional = reservationRepository.findById(payment.getReservationId());

        if (userOptional.isPresent() && reservationOptional.isPresent()) {
            User user = userOptional.get();
            Reservation reservation = reservationOptional.get();
            Optional<Event> eventOptional = generalEventRepository.findById(reservation.getEventId());
            Event generalEvent = eventOptional.get();

            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                Email email = new Email();
                String emailBody = "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
                        ".container { max-width: 800px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); }" +
                        ".header { background-color: #135bf2; color: white; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
                        ".content { padding: 20px; font-size: 16px; color: #333; }" +
                        ".content p { line-height: 1.6; }" +
                        ".amount { font-weight: bold; color: #135bf2; }" +
                        ".table { width: 100%; border-collapse: collapse; margin-top: 20px; }" +
                        ".table td { padding: 8px; border: 1px solid #dddddd; text-align: left; }" +
                        ".footer { margin-top: 20px; padding-top: 15px; border-top: 1px solid #dddddd; text-align: center; font-size: 13px; color: #777; }" +
                        ".footer p { margin: 5px 0; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<div class='header'><h2>Payment Confirmation " + " for " + reservation.getEventId() + "</h2></div>" +
                        "<div class='content'>" +
                        "<p>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>" +
                        "<p>Thank you for choosing BkTicketing for your reservation. Below are your payment details:</p>" +

                        "<table class='table'>" +
                        "<tr><td><strong>Email Address:</strong></td><td>" + user.getUserEmail() + "</td></tr>" +
                        "<tr><td><strong>Event Name:</strong></td><td>" + generalEvent.getEventName() + "</td></tr>" +
                        "<tr><td><strong>Reservation Date:</strong></td><td>" + user.getDateRegistered() + "</td></tr>" +
                        "<tr><td><strong>Reservation Time:</strong></td><td>" + user.getTimeRegistered() + "</td></tr>" +
                        "<tr><td><strong>Event Date:</strong></td><td>" + generalEvent.getEventDate() + "</td></tr>" +
                        "<tr><td><strong>Event Time:</strong></td><td>" + generalEvent.getStartTime() + "</td></tr>" +
                        "<tr><td><strong>Event Venue:</strong></td><td class='amount'>" + generalEvent.getEventVenue() + "</td></tr>" +
                        "<tr><td><strong>Number of Tickets:</strong></td><td>" + reservation.getNumOfTickets() + "</td></tr>" +
                        "<tr><td><strong>Amount Paid:</strong></td><td class='amount'>Rs" + payment.getAmount() + ".0</td></tr>" +
                        "<tr><td><strong>Transaction Description:</strong></td><td>" + payment.getDescription() + "</td></tr>" +
                        "</table>" +

                        "<p>We are thrilled to have you on board. Enjoy the event!</p>" +
                        "<p>Warm regards, <strong class='amount'>BkTicketing<sup>LK</sup></strong></p>" +
                        "</div>" +

                        "<div class='footer'>" +
                        "<p>&copy; 2024 BkTicketing LK. All rights reserved.</p>" +
                        "<p>If you have any questions, please contact us at support@bkticketing.lk</p>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                        "</html>";
                helper.setTo(user.getUserEmail());
                helper.setSubject("Payment Confirmation");
                helper.setText(emailBody, true);
                email.setToEmail(user.getUserEmail());
                email.setSubject("Payment Confirmation");
                email.setBody("Email sent to user Id: " + user.getUserId() + " and reservation id: " + reservation.getReservationId());
                emailRepository.save(email);
                mailSender.send(mimeMessage);
            } catch (Exception e) {
                System.out.println("Failed to send email: " + e.getMessage());
            }
        } else {
            System.out.println("User not found with ID: " + payment.getUserId());
        }
    }
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }
    public List<Payment> getPaymentByUserId(String userId) {
        return paymentRepository.findByUserId(userId);
    }
    public List<Payment> getPaymentByReservationId(String reservationId) {
        return paymentRepository.findByReservationId(reservationId);
    }
    public Optional<Payment> getPaymentById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }
    public void deletePayment(String paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
