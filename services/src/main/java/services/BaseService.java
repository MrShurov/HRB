package services;

import dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
public class BaseService<T> implements IService<T> {

    @Autowired
    private Dao<T> baseDao;

    public BaseService() {
    }

    @Autowired
    public BaseService(Dao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public T add(T t) {
        return baseDao.add(t);
    }

    public T update(T t) {
        return baseDao.update(t);
    }

    public T get(Serializable id) {
        return baseDao.get(id);
    }

    public void delete(T t) {
        baseDao.delete(t);
    }

    public void refresh(T t) {
        baseDao.refresh(t);
    }
}
