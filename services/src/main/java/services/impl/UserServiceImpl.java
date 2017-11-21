package services.impl;

import dao.IInfoDao;
import dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Info;
import pojos.User;
import services.BaseService;
import services.IUserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseService<User> implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IInfoDao infoDao;

    @Override
    public User getByUserName(String name) {
        return userDao.getByUserName(name);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User createUser(User user) {
        if (user != null) {
            if (isUnique(user)){
                Info info = new Info(100.0, null);
                user.setInfo(info);
                info.setUser(user);
                userDao.add(user);
                infoDao.add(info);
                return userDao.getByUserName(user.getUsername());
            }
        }
        return null;
    }

    @Override
    public boolean isUnique(User user) {
        boolean isUnique = true;
        List<String> list = userDao.getAllUsernames();
        for (String aList : list) {
            if (aList.equals(user.getUsername())) {
                isUnique = false;
                return isUnique;
            }
        }
        return isUnique;
    }
}
