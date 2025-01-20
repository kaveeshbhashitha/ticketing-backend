package com.ticketing.ticketing_backend.Controller;
import com.ticketing.ticketing_backend.Dto.DailyIncomeDto;
import com.ticketing.ticketing_backend.Implementation.PaymentServiceImplementation;
import com.ticketing.ticketing_backend.Model.Payment;
import com.ticketing.ticketing_backend.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"https://oficialticketing-frontend.netlify.app", "http://localhost:5173"})
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentServiceImplementation paymentService;
    @Autowired
    private PaymentRepository paymentRepository;
    public PaymentController(PaymentServiceImplementation paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping("/dailyIncome")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<DailyIncomeDto>> getDailyIncome() {
        List<DailyIncomeDto> dailyIncome = paymentRepository.aggregateDailyIncome(); // Implement aggregation in your repository
        return ResponseEntity.ok(dailyIncome);
    }
    @PostMapping("/process") // add method
    public Payment processPayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }
    @GetMapping("/getPaymentByUserId/{id}")
    public List<Payment> getPaymentByUserId(@PathVariable("id") String userId) {
        return paymentService.getPaymentByUserId(userId);
    }
    @GetMapping("/getPaymentByReservationId/{id}")
    public List<Payment> getPaymentByReservationId(@PathVariable("id") String reservationId) {
        return paymentService.getPaymentByReservationId(reservationId);
    }
    @GetMapping("/getAllPayment")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Payment> getAllPayment() {
        return paymentService.getAllPayment();
    }
    @GetMapping("/getPaymentById/{id}")
    public Optional<Payment> getPaymentById(@PathVariable("id") String paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
    @DeleteMapping("/deletePayment/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deletePayment(@PathVariable("id") String paymentId) {
        paymentService.deletePayment(paymentId);
        return "Payment deleted with id " + paymentId;
    }
}
