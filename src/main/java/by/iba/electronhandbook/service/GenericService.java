package by.iba.electronhandbook.service;

import by.iba.electronhandbook.bean.AbstractEntity;
import by.iba.electronhandbook.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface GenericService<T extends AbstractEntity> {
    List<T> getAll() throws ServiceException;
    List<T> add(Map<String, String[]> params) throws ServiceException;
    boolean delete(Map<String, String[]> params) throws ServiceException;
    List<T> update(Map<String, String[]> params) throws ServiceException;
    T getById(Map<String, String[]> params) throws ServiceException;
    List<?> getAllDto(String queryName, String[] params) throws ServiceException;
}

