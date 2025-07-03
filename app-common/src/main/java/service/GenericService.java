package service;

public interface GenericService<T> {
    T create(T dto);
    T update(String id, T dto);
    void delete(String id);
    T findById(String id);
    Object findAll();
}
