package dao.impl;

import dao.IInfoDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojos.Info;

@Repository
public class InfoDaoImpl extends BaseDao<Info> implements IInfoDao {

    @Autowired
    public InfoDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }
}
