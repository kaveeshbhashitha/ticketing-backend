package com.ticketing.ticketing_backend.Controller;
import com.ticketing.ticketing_backend.Model.Reservation;
import com.ticketing.ticketing_backend.Model.User;
import com.ticketing.ticketing_backend.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping("/getAllReservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
    @GetMapping("/getReservationById/{id}")
    public Optional<Reservation> getReservationById(@PathVariable("id") String reservationId) {
        return reservationService.getReservationById(reservationId);
    }
    @GetMapping("/getReservationByUserId/{id}")
    public List<Reservation> getReservationByUserId(@PathVariable("id") String userId) {
        return reservationService.getReservationByUserId(userId);
    }
    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }
    @PutMapping("/updateReservation/{id}")
    public Reservation updateReservation(@PathVariable("id") String reservationId,@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservationId, reservation);
    }
    @DeleteMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable("id") String reservationId) {
        reservationService.deleteReservation(reservationId);
        return "Match deleted with ID " + reservationId;
    }
    @GetMapping("/totalCharge/today")
    public ResponseEntity<Double> getTotalChargeByCurrentDate() {
        return ResponseEntity.ok(reservationService.getTotalChargeByCurrentDate());
    }
    @GetMapping("/totalCharge")
    public ResponseEntity<Double> getTotalCharge() {
        return ResponseEntity.ok(reservationService.getTotalCharge());
    }
    @GetMapping("/totalTickets/today")
    public ResponseEntity<Integer> getTotalTicketsByCurrentDate() {
        return ResponseEntity.ok(reservationService.getTotalTicketsByCurrentDate());
    }
    @GetMapping("/totalTickets")
    public ResponseEntity<Integer> getTotalTickets() {
        return ResponseEntity.ok(reservationService.getTotalTickets());
    }
    @PostMapping("/cancelReservation")
    public Reservation cancelReservation(@RequestParam String reservationId) {
        return reservationService.updateReservationStatus(reservationId);
    }
}
