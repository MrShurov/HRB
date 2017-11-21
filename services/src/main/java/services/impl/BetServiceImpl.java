package services.impl;

import dao.IBetDao;
import dao.ITeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Bet;
import pojos.Team;
import services.BaseService;
import services.IBetService;
import services.ITeamService;

import java.util.List;

@Service
@Transactional
public class BetServiceImpl extends BaseService<Bet> implements IBetService{
    @Autowired
    private IBetDao betDao;

    @Override
    public List getAllByUserId(Long userId) {
        return betDao.getAllByUserId(userId);
    }
}
