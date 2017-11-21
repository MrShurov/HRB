package services.impl;

import dao.ITeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Team;
import services.BaseService;
import services.ITeamService;

import java.util.List;

@Service
@Transactional
public class TeamServiceImpl extends BaseService<Team> implements ITeamService {
    @Autowired
    private ITeamDao teamDao;

    @Override
    public List<Team> getAll() {
        return teamDao.getAll();
    }

    @Override
    public Team getByName(String name) {
        return teamDao.getByName(name);
    }
}
