package by.iba.electronhandbook.dao;

import by.iba.electronhandbook.exception.DaoException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public abstract class MySqlAbstractDao{
    private static final Logger logger;
    private static HikariDataSource dataSource;

    static {
        try{
            logger = LogManager.getLogger(MySqlAbstractDao.class.getName());
            Class.forName(Driver.class.getName());
            initConnectionPool();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void initConnectionPool() throws DaoException{
        InputStream inputStream =null;
        Properties properties=new Properties();
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
            dataSource = new HikariDataSource( new HikariConfig(properties));
        }catch (IOException e){
            throw new DaoException(e);
        }finally {
            try {
                if(inputStream !=null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    public final Connection getConnection() throws DaoException{
        Connection connection;
        try{
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection, Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    protected void setParam(PreparedStatement statement, String param, int paramNumber) throws SQLException{
        int type = statement.getParameterMetaData().getParameterType(paramNumber);
        switch (JDBCType.valueOf(type)){
            case INTEGER: statement.setInt(paramNumber, Integer.parseInt(param));
                break;
            case VARCHAR: statement.setString(paramNumber, param);
                break;
            case DOUBLE: statement.setDouble(paramNumber, Double.parseDouble(param));
                break;
            case FLOAT: statement.setFloat(paramNumber, Float.parseFloat(param));
                break;
        }
    }
}
