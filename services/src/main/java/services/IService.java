package services;

import java.io.Serializable;

public interface IService<T> {
    T add(T t);

    T update(T t);

    T get(Serializable id);

    void delete(T t);

    void refresh(T t);
}
