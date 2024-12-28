package com.ticketing.ticketing_backend.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "reservation")
public class Reservation {
    @Id
    private String reservationId;
    private String userId;
    private String eventId;
    private Integer numOfTickets;
    private Double totalCharge;
    private Double perTicketCharge;
    private String status = "Available";
    private LocalDate reservationDate = LocalDate.now();
    private LocalTime reservationTime = LocalTime.now();

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(Integer numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Double getPerTicketCharge() {
        return perTicketCharge;
    }

    public void setPerTicketCharge(Double perTicketCharge) {
        this.perTicketCharge = perTicketCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}
