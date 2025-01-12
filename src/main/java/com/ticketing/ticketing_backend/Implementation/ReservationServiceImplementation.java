package com.ticketing.ticketing_backend.Implementation;
import com.ticketing.ticketing_backend.Model.Reservation;
import com.ticketing.ticketing_backend.Model.User;
import com.ticketing.ticketing_backend.Repository.ReservationRepository;
import com.ticketing.ticketing_backend.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImplementation implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    public ReservationServiceImplementation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    @Override
    public List<Reservation> getReservationByUserId(String userId) {
        return reservationRepository.findReservationByUserId(userId);
    }
    @Override
    public Optional<Reservation> getReservationById(String reservationId) {
        return reservationRepository.findById(reservationId);
    }
    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    @Override
    public Reservation updateReservation(String reservationId, Reservation reservationDetail) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setEventId(reservationDetail.getEventId());
            reservation.setUserId(reservationDetail.getUserId());
            reservation.setReservationDate(reservationDetail.getReservationDate());
            reservation.setReservationTime(reservationDetail.getReservationTime());
            reservation.setNumOfTickets(reservationDetail.getNumOfTickets());
            reservation.setPerTicketCharge(reservationDetail.getPerTicketCharge());
            reservation.setTotalCharge(reservationDetail.getTotalCharge());
            reservation.setStatus(reservationDetail.getStatus());
            return reservationRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation not found with id " + reservationId));
    }

    @Override
    public Reservation updateReservationStatus(String reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            throw new RuntimeException("No user found with email: " + reservationId);
        }
        Reservation reservation = optionalReservation.get();
        reservation.setStatus("Cancelled");
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }
    @Override
    public double getTotalChargeByCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return reservationRepository.findTotalChargesByDate(currentDate)
                .stream()
                .mapToDouble(Reservation::getTotalCharge)
                .sum();
    }
    @Override
    public double getTotalCharge() {
        return reservationRepository.findAllTotalCharges()
                .stream()
                .mapToDouble(Reservation::getTotalCharge)
                .sum();
    }
    @Override
    public int getTotalTicketsByCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return reservationRepository.findNumOfTicketsByDate(currentDate)
                .stream()
                .mapToInt(Reservation::getNumOfTickets)
                .sum();
    }
    @Override
    public int getTotalTickets() {
        return reservationRepository.findAllNumOfTickets()
                .stream()
                .mapToInt(Reservation::getNumOfTickets)
                .sum();
    }
}
