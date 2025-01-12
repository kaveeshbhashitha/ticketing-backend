package com.ticketing.ticketing_backend.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketing.ticketing_backend.Model.Event;
import com.ticketing.ticketing_backend.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping("/addEvent")
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
    public Event getEventById(@PathVariable String eventId) {
        return eventService.getEventById(eventId);
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
    @PutMapping("/update{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") String eventId,
                                             @RequestParam("event") String eventJson,
                                             @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
        // Convert eventJson to Event object (you can use a library like Jackson to deserialize JSON)
        Event updatedEvent = new ObjectMapper().readValue(eventJson, Event.class);

        eventService.updateEvent(eventId, updatedEvent, imageFile);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully.");
    }
}
