package dao;

import pojos.Team;

import java.sql.SQLException;
import java.util.List;

public interface ITeamDao extends Dao<Team>{
    Team getByName(String name);
    List<Team> getAll();
}
