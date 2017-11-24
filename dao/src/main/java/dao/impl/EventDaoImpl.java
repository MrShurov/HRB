package dao.impl;

import dao.IEventDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojos.Event;

import java.util.List;

@Repository
public class EventDaoImpl extends BaseDao<Event> implements IEventDao {

    @Autowired
    public EventDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    @Override
    public List<Event> getAll() {
        String hql = "select e from Event e";
        Query query = getSession().createQuery(hql);
        return query.getResultList();
    }


}
