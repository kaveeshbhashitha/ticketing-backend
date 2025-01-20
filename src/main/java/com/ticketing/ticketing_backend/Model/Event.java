package com.ticketing.ticketing_backend.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "event")
public class Event {
    @Id
    private String eventId;
    private String eventName;
    private String eventDate;
    private String startTime;
    private String endTime;
    private String eventVenue;
    private String eventOrganizer;
    private String description;
    private Double oneTicketPrice;
    private String eventType;
    private String eventIsFor;
    private Integer numOfTickets;
    private String teamOne;
    private String teamTwo;
    private Integer maxPerson;
    private String duration;
    private String theaterTime1;
    private String theaterTime2;
    private String theaterIsFor;
    private String status;
    private String videoId;
    private LocalDate dateAdded = LocalDate.now();
    private LocalTime timeAdded = LocalTime.now();

    // Image related fields
    private String imageName;
    private byte[] imageData;
    private String contentType;

    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getEventVenue() {
        return eventVenue;
    }
    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }
    public String getEventOrganizer() {
        return eventOrganizer;
    }
    public void setEventOrganizer(String eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getOneTicketPrice() {
        return oneTicketPrice;
    }
    public void setOneTicketPrice(Double oneTicketPrice) {
        this.oneTicketPrice = oneTicketPrice;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public String getEventIsFor() {
        return eventIsFor;
    }
    public void setEventIsFor(String eventIsFor) {
        this.eventIsFor = eventIsFor;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getNumOfTickets() {
        return numOfTickets;
    }
    public void setNumOfTickets(Integer numOfTickets) {
        this.numOfTickets = numOfTickets;
    }
    public String getTeamOne() {
        return teamOne;
    }
    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }
    public String getTeamTwo() {
        return teamTwo;
    }
    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }
    public Integer getMaxPerson() {
        return maxPerson;
    }
    public void setMaxPerson(Integer maxPerson) {
        this.maxPerson = maxPerson;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getTheaterTime1() {
        return theaterTime1;
    }
    public void setTheaterTime1(String theaterTime1) {
        this.theaterTime1 = theaterTime1;
    }
    public String getTheaterTime2() {
        return theaterTime2;
    }
    public void setTheaterTime2(String theaterTime2) {
        this.theaterTime2 = theaterTime2;
    }
    public String getTheaterIsFor() {
        return theaterIsFor;
    }
    public void setTheaterIsFor(String theaterIsFor) {
        this.theaterIsFor = theaterIsFor;
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
    public LocalTime getTimeAdded() {
        return timeAdded;
    }
    public void setTimeAdded(LocalTime timeAdded) {
        this.timeAdded = timeAdded;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getVideoId() {
        return videoId;
    }
    public String setVideoId(String videoId) {
        return this.videoId= videoId;
    }

    
}
