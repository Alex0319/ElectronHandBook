package by.iba.electronhandbook.dao;

import by.iba.electronhandbook.bean.AbstractEntity;
import by.iba.electronhandbook.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class MySqlGenericDaoImpl<T extends AbstractEntity> extends MySqlAbstractDao implements GenericDao<T> {
    private final String GET_ALL;
    private final String GET_BY_ID;
    private final String ADD;
    private final String UPDATE;
    private final String REMOVE;

    private int lastInsertedId;

    public MySqlGenericDaoImpl(String getAll, String getById, String add, String update, String remove){
        GET_ALL = getAll;
        GET_BY_ID = getById;
        ADD = add;
        UPDATE = update;
        REMOVE = remove;
    }

    protected int getLastInsertedId(){
        return lastInsertedId;
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
        Connection connection = null;
        try {
            connection = getConnection();
            addEntity(connection, entity);
        } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection, null);
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
        Connection connection = null;
        try {
            connection = getConnection();
            updateEntity(connection, entity);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection, null);
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

    private final void fillStatementQueryCondition(PreparedStatement statement, String[] params) throws SQLException{
        int paramsCount = statement.getParameterMetaData().getParameterCount();
        if(paramsCount != 0){
            for(int i = 0; i < paramsCount; i++){
                setParam(statement, params[i], i+1);
            }
        }
    }

    protected void addEntity(Connection connection, T entity) throws SQLException{
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            statement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            fillStatement(statement, entity);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                lastInsertedId = (int)resultSet.getLong(1);
            }
        }finally {
            closeStatement(statement);
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

    protected final <E extends AbstractEntity> String getRelatedEntityIds(Set<E> relatedEntities){
        if(relatedEntities == null){
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (E entity: relatedEntities) {
            builder.append(entity.getId());
            builder.append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    protected abstract T fillEntity(ResultSet resultSet) throws SQLException;
    protected abstract void fillStatement(PreparedStatement statement, T entity) throws SQLException;
}
