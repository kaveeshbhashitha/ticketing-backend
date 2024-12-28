package com.ticketing.ticketing_backend.Implementation;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.ticketing.ticketing_backend.Model.Event;
import com.ticketing.ticketing_backend.Repository.EventRepository;
import com.ticketing.ticketing_backend.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplementation implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event addEvent(Event event, MultipartFile imageFile) throws IOException {
        event.setImageName(imageFile.getOriginalFilename());
        event.setContentType(imageFile.getContentType());
        event.setImageData(imageFile.getBytes());
        return eventRepository.save(event);
    }
    public Event getEventById(String eventId){
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new RuntimeException("Event not found with id: " + eventId);
        }
    }
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public List<Event> getByEventName(String name) {
        return eventRepository.findByEventName(name);
    }
    @Override
    public List<Event> getByEventDate(LocalDate date) {
        return eventRepository.findByEventDate(date);
    }
    @Override
    public List<Event> getByEventVenue(String venue) {
        return eventRepository.findByEventVenue(venue);
    }
    @Override
    public void updateEvent(String id, Event updatedEvent) {
        Event existingEvent = getEventById(id);
        updatedEvent.setEventId(existingEvent.getEventId());
        eventRepository.save(updatedEvent);
    }
    @Override
    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

}
