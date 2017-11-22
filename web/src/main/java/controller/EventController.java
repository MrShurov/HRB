package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pojos.Event;
import pojos.User;
import services.IEventService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/events")
public class EventController {
    private static final String MAIN = "events/main";

    @Autowired
    private IEventService eventService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) {
        fillModel(modelMap);
        return MAIN;
    }

    private void fillModel(ModelMap modelMap) {
        populatePageName(modelMap);
        List<Event> list = eventService.getAll();
        modelMap.put("events", list);
    }

    private void populatePageName(ModelMap modelMap) {
        modelMap.addAttribute("currentPageName", "events");
    }
}
