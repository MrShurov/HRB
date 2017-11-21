package dao.impl;

import dao.IBetDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojos.Bet;

import java.util.List;

@Repository
public class BetDaoImpl extends BaseDao<Bet> implements IBetDao {

    @Autowired
    public BetDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    //Bag in 5 version of Hirbernate
    @Override
    public List getAllByUserId(Long userId) {
        Query query = getSession().createQuery("select b from Bet b where b.userId=:id")
                .setParameter("id", userId);
        return query.getResultList();
    }
}
