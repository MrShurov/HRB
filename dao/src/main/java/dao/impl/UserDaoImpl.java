package dao.impl;

import dao.IUserDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojos.User;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDao<User> implements IUserDao {

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    @Override
    public List<User> getAll() {
        String hql = "FROM User";
        Query query = getSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public User getByUserName(String name) {
        String hql = "select u from User u where u.username=:name";
        Query query = getSession().createQuery(hql).setParameter("name", name);
        return (User)query.getSingleResult();
    }

    @Override
    public List<String> getAllUsernames() {
        String hql = "select username from User";
        Query query = getSession().createQuery(hql);
        return query.getResultList();
    }
}
