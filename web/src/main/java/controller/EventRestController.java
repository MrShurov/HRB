package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojos.Event;
import pojos.TeamsForCreate;
import services.IEventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventRestController {
    @Autowired
    private IEventService eventService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = eventService.getAll();
        if(events.isEmpty()){ return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Event> updateEvent(@PathVariable("id") Integer id, @RequestBody Event newEvent) {
        Event event = eventService.get(id);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        event.setEventResult(newEvent.getEventResult());
        newEvent = eventService.update(event);
        return new ResponseEntity<Event>(newEvent, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.delete(eventService.get(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Event> addEvent(TeamsForCreate teamsForCreate) {
        Event newEvent = eventService.addEvent(teamsForCreate);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }
}