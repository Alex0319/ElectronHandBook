package by.iba.electronhandbook.dao;

import by.iba.electronhandbook.bean.AbstractEntity;
import by.iba.electronhandbook.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MySqlGenericDaoImpl<T extends AbstractEntity> extends MySqlAbstractDao implements GenericDao<T> {
    private final String GET_ALL;
    private final String GET_BY_ID;
    private final String ADD;
    private final String UPDATE;
    private final String REMOVE;

    public MySqlGenericDaoImpl(String getAll, String getById, String add, String update, String remove){
        GET_ALL = getAll;
        GET_BY_ID = getById;
        ADD = add;
        UPDATE = update;
        REMOVE = remove;
    }

    @Override
    public List<T> getAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> entities = new ArrayList<>();
        Connection connection = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(GET_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                entities.add(fillEntity(resultSet));
            }
            if(entities.size() == 0){
                entities.add(fillEntity(null));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            closeConnection(connection, statement);
        }
        return entities;
    }

    public List<T> getAllCorrespondingToCondition(String query, String[] params) throws DaoException{
        PreparedStatement statement = null;
        ResultSet resultSet;
        List<T> entities = new ArrayList<>();
        Connection connection = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(query);
            fillStatementQueryCondition(statement, params);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                entities.add(fillEntity(resultSet));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            closeConnection(connection, statement);
        }
        return entities;
    }

    @Override
    public void add(T entity) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ADD);
            fillStatement(statement, entity);
            statement.execute();
        } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection, statement);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(REMOVE);
            statement.setInt(1, id);
            statement.execute();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection, statement);
        }
    }

    @Override
    public void update(int id, T entity) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            updateEntity(connection, entity);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection, statement);
        }
    }

    @Override
    public T getById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T entity = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entity = fillEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return entity;
    }

    private void fillStatementQueryCondition(PreparedStatement statement, String[] params) throws SQLException{
        int paramsCount = statement.getParameterMetaData().getParameterCount();
        if(paramsCount != 0){
            for(int i = 0; i < paramsCount; i++){
                setParam(statement, params[i], i+1);
            }
        }
    }

    protected void updateEntity(Connection connection, T entity) throws SQLException{
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            fillStatement(statement, entity);
            statement.executeUpdate();
        }finally {
            closeStatement(statement);
        }
    }

    protected abstract T fillEntity(ResultSet resultSet) throws SQLException;
    protected abstract void fillStatement(PreparedStatement statement, T entity) throws SQLException;
}
