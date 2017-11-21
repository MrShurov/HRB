package dao;

import pojos.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao extends Dao<User> {
    User getByUserName(String name);
    List<User> getAll();
    List<String> getAllUsernames();
}
