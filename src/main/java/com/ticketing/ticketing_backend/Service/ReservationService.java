package com.ticketing.ticketing_backend.Service;
import com.ticketing.ticketing_backend.Model.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> getAllReservations();
    List<Reservation> getReservationByUserId(String userId);
    Optional<Reservation> getReservationById(String reservationId);
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(String reservationId, Reservation reservationDetail);
    void deleteReservation(String reservationId);
    double getTotalChargeByCurrentDate();
    double getTotalCharge();
    int getTotalTicketsByCurrentDate();
    int getTotalTickets();
}
