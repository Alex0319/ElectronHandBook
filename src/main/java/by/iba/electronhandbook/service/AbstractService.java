package by.iba.electronhandbook.service;

import by.iba.electronhandbook.bean.AbstractEntity;
import by.iba.electronhandbook.dao.GenericDao;
import by.iba.electronhandbook.exception.DaoException;
import by.iba.electronhandbook.exception.ServiceException;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractService<T extends AbstractEntity> implements GenericService<T> {
    protected GenericDao<T> genericDao;
    private T entity;
    protected int id;

    public AbstractService(GenericDao<T> genericDao){
        this.genericDao = genericDao;
    }

    @Override
    public List<T> getAll() throws ServiceException {
        try {
            return genericDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<T> add(Map<String, String[]> params) throws ServiceException {
        try {
            genericDao.add(buildEntity(params));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return getAll();
    }

    @Override
    public boolean delete(Map<String, String[]> params) throws ServiceException {
        try {
            entity = buildEntity(params);
            genericDao.delete(entity.getId());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public List<T> update(Map<String, String[]> params) throws ServiceException {
        try {
            entity = buildEntity(params);
            genericDao.update(id, entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return getAll();
    }

    @Override
    public T getById(Map<String, String[]> params) throws ServiceException {
        try {
            entity = buildEntity(params);
            return genericDao.getById(entity.getId());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    protected boolean tryParseDouble(String value){
        try {
            Double.parseDouble(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    protected List<T> getMatches(String matchName, String[] matchParams) throws ServiceException{
        try {
            return genericDao.getAllCorrespondingToCondition(matchName, matchParams);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    protected final <E extends AbstractEntity> Set<E> getRelatedEntityIds(String[] values, Class<E> entityClass) throws ServiceException{
        try {
            Set<E> entities = new LinkedHashSet<>();
            for (String id: values) {
                E entity = entityClass.newInstance();
                entity.setId(Integer.parseInt(id));
                entities.add(entity);
            }
            return entities;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServiceException(e);
        }
    }

    protected abstract T buildEntity(Map<String, String[]> params) throws ServiceException;
}
