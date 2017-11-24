package services.impl;

import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.*;
import services.BaseService;
import services.IEventService;
import services.IUserService;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl extends BaseService<Event> implements IEventService{
    @Autowired
    private IEventDao eventDao;
    @Autowired
    private ITeamDao teamDao;
    @Autowired
    private IBetDao betDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IInfoDao infoDao;

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }

    @Override
    public Event addEvent(TeamsForCreate teamsForCreate) {
        if(isNameExist(teamsForCreate.getTeam1()) && isNameExist(teamsForCreate.getTeam2())){
                Team team1 = teamDao.getByName(teamsForCreate.getTeam1());
                Team team2 = teamDao.getByName(teamsForCreate.getTeam2());
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

    public Event resultEvent(Event event){
        if (event.getEventResult().equals("W1") || event.getEventResult().equals("DRAW") || event.getEventResult().equals("W2")){
            event = eventDao.update(event);
            List betList = betDao.getAllByEventId(event.getId());
            for (Object aBetList : betList) {
                Bet bet = (Bet) aBetList;
                if (bet.getValueBet().equals(event.getEventResult())) {
                    bet.setStatus("WIN");
                    Info info = infoDao.get(userDao.get(bet.getUserId()).getInfo().getId());
                    info.setBalance(info.getBalance() + bet.getPossibleWin());
                } else {
                    bet.setStatus("LOSE");
                }
            }
        }
        return event;
    }

    private double getCoefficient(Team team){
        return ((double)(team.getWinAmount()+team.getLoseAmount())/team.getWinAmount());
    }

    private boolean isNameExist(String str){
        boolean isNameExist = false;
        List<String> list = teamDao.getAllNames();
        for (String aList : list) {
            if (aList.equals(str)) {
                isNameExist = true;
                return isNameExist;
            }
        }
        return isNameExist;
    }
}
