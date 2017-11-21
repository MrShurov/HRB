package services;

import pojos.User;

import java.util.List;

public interface IUserService extends IService<User> {
    User getByUserName(String name);
    List<User> getAll();
    User createUser(User user);
    boolean isUnique(User user);
}
