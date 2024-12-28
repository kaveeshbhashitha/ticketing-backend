package com.ticketing.ticketing_backend.Service;
import com.ticketing.ticketing_backend.Model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment processPayment(Payment payment);
    void sendPaymentSuccessEmail(Payment payment);
    List<Payment> getAllPayment();
    List<Payment> getPaymentByUserId(String userId);
    List<Payment> getPaymentByReservationId(String reservationId);
    Optional<Payment> getPaymentById(String paymentId);
    void deletePayment(String paymentId);
}
