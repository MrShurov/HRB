package dao.impl;

import dao.ITeamDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojos.Team;

import java.util.List;

@Repository
public class TeamDaoImpl extends BaseDao<Team> implements ITeamDao {

    @Autowired
    public TeamDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    @Override
    public List<Team> getAll() {
        String hql = "select t from Team t";
        Query query = getSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Team getByName(String name) {
        String hql = "select t from Team t where t.name=:name";
        Query query = getSession().createQuery(hql).setParameter("name", name);
        return (Team)query.getSingleResult();
    }
}
