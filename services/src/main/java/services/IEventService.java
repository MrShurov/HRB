package services;

import pojos.Event;

import java.util.List;

public interface IEventService extends IService<Event> {
    List<Event> getAll();
    Event addEvent(String str1,String str2);
}
