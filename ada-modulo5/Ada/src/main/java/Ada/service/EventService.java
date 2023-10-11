package Ada.service;

import Ada.exception.InvalidException;
import Ada.model.DataDocumentation;
import Ada.model.Event;
import Ada.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.util.Optional;
import java.util.regex.Pattern;

public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) throws InvalidException {
        switch (event.getEventType()) {
            case CLIENTE, FUNCIONÁRIO -> {
                if (isPhysicalPerson(event)) {
                    return eventRepository.save(event);
                }
            }
            case FORNECEDOR -> {
                if (isCompany(event)) {
                    return eventRepository.save(event);
                }
            }
        }
        throw new InvalidException();
    }

    public Optional<Event> getEvent(Long id){
        return eventRepository.findById(id);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    public Event updateParty(Long id, Event p){
        Optional<Event> party = getEvent(id);
        if(party.isPresent()){
            party.get().setDate(p.getDate());
            party.get().setName(p.getName());
            return eventRepository.save(party.get());
        }else{
            throw new EntityNotFoundException();
        }
    }

    private boolean isPhysicalPerson(Event event){
        Pattern p = Pattern.compile("CPF|RG");
        boolean found = false;
        for (DataDocumentation s : event.getDocuments()) {
            if (p.matcher(s.getType().name()).find()) {
                found = true;
                break;
            }
        }
        return found;
    }

    private boolean isCompany(Event event){
        Pattern p = Pattern.compile("CNPJ");
        boolean found = false;
        for (DataDocumentation s : event.getDocuments()) {
            if (p.matcher(s.getType().name()).find()) {
                found = true;
                break;
            }
        }
        return found;
    }

}
