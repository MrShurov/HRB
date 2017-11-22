package services;

import pojos.Event;
import pojos.Team;
import pojos.TeamsForCreate;

import java.util.List;

public interface IEventService extends IService<Event> {
    List<Event> getAll();
    Event addEvent(TeamsForCreate teamsForCreate);
}
