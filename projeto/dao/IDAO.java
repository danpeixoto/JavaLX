package projeto.dao;

import java.util.List;

public interface IDAO<T> {

    void add(T t) throws Exception;
    void remove(T t);
    void remove(int id);
    void update(T t);
    List<T> getAll();
    T getById(int id);

}
