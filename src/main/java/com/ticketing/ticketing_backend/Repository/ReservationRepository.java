package com.ticketing.ticketing_backend.Repository;
import com.ticketing.ticketing_backend.Model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findReservationByUserId(String userId);
    @Query(value = "{'reservationDate': ?0}", fields = "{'totalCharge': 1}")
    List<Reservation> findTotalChargesByDate(LocalDate date);
    @Query(value = "{'reservationDate': ?0}", fields = "{'numOfTickets': 1}")
    List<Reservation> findNumOfTicketsByDate(LocalDate date);
    @Query(value = "{}", fields = "{'totalCharge': 1}")
    List<Reservation> findAllTotalCharges();
    @Query(value = "{}", fields = "{'numOfTickets': 1}")
    List<Reservation> findAllNumOfTickets();
}
