package Ada.controller;
import Ada.exception.InvalidException;
import Ada.model.Event;
import Ada.service.DataService;
import Ada.service.EventService;
import com.sun.jdi.InvalidTypeException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private DataService dataService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) throws InvalidTypeException {
        log.info("Create Party [POST] {}",event.toString());
        dataService.saveDocument(event.getDocuments());
        try {
            eventService.saveEvent(event);
        } catch (InvalidException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Event>> getEvent(@PathVariable Long id){
        Optional<Event> event = eventService.getEvent(id);
        if(event.isPresent()){
            log.info("Get Party [GET] {}",event.toString());
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteParty(@PathVariable Long id){
        Optional<Event> party = eventService.getEvent(id);
        if(party.isPresent()){
            log.info("Delete Party [DELETE] {}",party.toString());
            eventService.deleteEvent(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateParty(@PathVariable Long id,@Valid @RequestBody Event event){
        log.info("Update Party [PATCH] {}",event.toString());
        Event e = eventService.updateParty(id,event);
        return ResponseEntity.ok(e);
    }
}
