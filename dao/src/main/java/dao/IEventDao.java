package dao;

import pojos.Event;

import java.util.List;

public interface IEventDao extends Dao<Event> {
    List<Event> getAll();
}
