package by.iba.electronhandbook.dao;

import by.iba.electronhandbook.bean.AbstractEntity;
import by.iba.electronhandbook.exception.DaoException;

import java.util.List;

public interface GenericDao<T extends AbstractEntity> {
    List<T> getAll() throws DaoException;
    List<T> getAllCorrespondingToCondition(String query, String[] conditionParams) throws DaoException;
    void add(T entity) throws DaoException;
    void delete(int id) throws DaoException;
    void update(int id, T entity) throws DaoException;
    T getById(int id) throws DaoException;
}
