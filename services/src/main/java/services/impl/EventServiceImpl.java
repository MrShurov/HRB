package services.impl;

import dao.IEventDao;
import dao.ITeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Event;
import pojos.Team;
import services.BaseService;
import services.IEventService;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl extends BaseService<Event> implements IEventService{
    @Autowired
    private IEventDao eventDao;
    @Autowired
    private ITeamDao teamDao;

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }

    @Override
    public Event addEvent(String str1,String str2) {
        if (str1 != null && str2 != null){
            Team team1 = teamDao.getByName(str1);
            Team team2 = teamDao.getByName(str2);
            Event event = new Event();
            event.setTeam1(team1);
            event.setTeam2(team2);
            event.setWin1Coefficient(getCoefficient(team1));
            event.setWin2Coefficient(getCoefficient(team2));
            event.setDrawCoefficient((getCoefficient(team1)+getCoefficient(team2))/2);
            return eventDao.add(event);
        }
        return null;
    }

    private double getCoefficient(Team team){
        return (team.getWinAmount()+team.getLoseAmount())/team.getWinAmount();
    }
}
