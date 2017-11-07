package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.User;
import by.iba.electronhandbook.bean.dto.UserDto;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.iba.electronhandbook.constants.Constants.*;

public class UserDaoImpl extends MySqlGenericDaoImpl<UserDto> {
    public UserDaoImpl() {
        super(GET_ALL_USERS, GET_USER, ADD_USER, UPDATE_USER, DELETE_USER);
    }

    @Override
    protected User fillEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        if(resultSet != null){
            user.setId(resultSet.getInt("USER_ID"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setRole(resultSet.getString("ROLE"));
        }
        return user;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, UserDto entity) throws SQLException {
        User user = (User)entity;
        statement.setString(1, user.getPassword());
        statement.setString(2, entity.getRole());
    }
}
