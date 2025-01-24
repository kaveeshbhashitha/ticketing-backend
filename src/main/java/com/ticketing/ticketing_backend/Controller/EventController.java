package com.ticketing.ticketing_backend.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketing.ticketing_backend.Model.Event;
import com.ticketing.ticketing_backend.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://oficialticketing-frontend.netlify.app", "http://localhost:5173"})
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping("/addEvent")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addEvent(@RequestPart("event") String eventJson, @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Event event = objectMapper.readValue(eventJson, Event.class);
            Event eventExist = eventService.addEvent(event, imageFile);
            return new ResponseEntity<>(eventExist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getEvent/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable String eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Event>> getByEventName(@PathVariable String name) {
        return ResponseEntity.ok(eventService.getByEventName(name));
    }
    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<Event>> getByEventDate(@PathVariable String date) {
        return ResponseEntity.ok(eventService.getByEventDate(LocalDate.parse(date)));
    }
    @GetMapping("/getByVenue/{venue}")
    public ResponseEntity<List<Event>> getByEventVenue(@PathVariable String venue) {
        return ResponseEntity.ok(eventService.getByEventVenue(venue));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateEvent(
            @PathVariable String id,
            @RequestParam("eventName") String eventName,
            @RequestParam("eventDate") String eventDate,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("eventVenue") String eventVenue,
            @RequestParam("oneTicketPrice") Double oneTicketPrice,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            eventService.updateEvent(id, eventName, eventDate, startTime, endTime, eventVenue, oneTicketPrice, description, image);
            return ResponseEntity.ok("Event updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update event: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully.");
    }

    @PutMapping("/cancel/{eventId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> cancelEvent(@PathVariable String eventId) {
        boolean isCancelled = eventService.cancelEvent(eventId);
        if (isCancelled) {
            return ResponseEntity.ok("Event has been canceled.");
        } else {
            return ResponseEntity.status(404).body("Event not found.");
        }
    }
    @PutMapping("/reschedule/{eventId}")
    public ResponseEntity<String> rescheduleEvent(@PathVariable String eventId) {
        boolean isRescheduled = eventService.rescheduleEvent(eventId);
        if (isRescheduled) {
            return ResponseEntity.ok("Event has been reschedule.");
        } else {
            return ResponseEntity.status(404).body("Event not found.");
        }
    }

}
