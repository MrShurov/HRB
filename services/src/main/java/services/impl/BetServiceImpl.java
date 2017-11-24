package services.impl;

import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Bet;
import pojos.Event;
import pojos.Info;
import pojos.Team;
import services.BaseService;
import services.IBetService;
import services.IEventService;
import services.ITeamService;

import java.util.List;

@Service
@Transactional
public class BetServiceImpl extends BaseService<Bet> implements IBetService{
    @Autowired
    private IBetDao betDao;
    @Autowired
    private IEventDao eventDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IInfoDao infoDao;

    @Override
    public List getAllByUserId(Long userId) {
        return betDao.getAllByUserId(userId);
    }

    @Override
    public String createBet(Bet bet) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pojos.User user1 = userDao.getByUserName(user.getUsername());
        bet.setUserId(user1.getId());
        if (bet.getPossibleWin() <= user1.getInfo().getBalance()){
            Event event = eventDao.get(bet.getEvent());
            if(event.getEventResult() == null){
                Info info = infoDao.get(user1.getInfo().getId());
                switch (bet.getValueBet()) {
                    case "W1":
                        info.setBalance(info.getBalance() - bet.getPossibleWin());
                        bet.setPossibleWin(event.getWin1Coefficient() * bet.getPossibleWin());
                        infoDao.update(info);
                        break;
                    case "DRAW":
                        info.setBalance(info.getBalance() - bet.getPossibleWin());
                        bet.setPossibleWin(event.getDrawCoefficient() * bet.getPossibleWin());
                        infoDao.update(info);
                        break;
                    case "W2":
                        info.setBalance(info.getBalance() - bet.getPossibleWin());
                        bet.setPossibleWin(event.getWin2Coefficient() * bet.getPossibleWin());
                        infoDao.update(info);
                        break;
                    default:
                        return "Ошибка в результате";
                }
                betDao.add(bet);
                return "Ставка сделана";
            }
            return "Событие уже прошло";
        }
        return "Недостаточно средств";
    }
}
