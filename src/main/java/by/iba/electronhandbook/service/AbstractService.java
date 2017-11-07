package by.iba.electronhandbook.service;

import by.iba.electronhandbook.bean.AbstractEntity;
import by.iba.electronhandbook.dao.GenericDao;
import by.iba.electronhandbook.exception.DaoException;
import by.iba.electronhandbook.exception.ServiceException;

import java.util.List;
import java.util.Map;

public abstract class AbstractService<T extends AbstractEntity> implements GenericService<T> {
    private GenericDao<T> genericDao;
    protected T entity;
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
    public void add(Map<String, String[]> params) throws ServiceException {
        try {
            genericDao.add(buildEntity(params));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Map<String, String[]> params) throws ServiceException {
        try {
            entity = buildEntity(params);
            genericDao.delete(entity.getId());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Map<String, String[]> params) throws ServiceException {
        try {
            entity = buildEntity(params);
            genericDao.update(id, entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public T getById(Map<String, String[]> params) throws ServiceException {
        try {
             buildEntity(params);
            return genericDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    protected abstract T buildEntity(Map<String, String[]> params) throws ServiceException;
}
