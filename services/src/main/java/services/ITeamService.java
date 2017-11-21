package services;

import pojos.Team;

import java.util.List;

public interface ITeamService extends IService<Team> {
    Team getByName(String name);
    List<Team> getAll();
}
