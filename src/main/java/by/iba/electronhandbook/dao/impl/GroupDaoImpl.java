package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.Group;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.iba.electronhandbook.constants.Constants.*;

public class GroupDaoImpl extends MySqlGenericDaoImpl<Group>{
    public GroupDaoImpl() {
        super(GET_ALL_GROUPS, GET_GROUP, ADD_GROUP, UPDATE_GROUP, DELETE_GROUP);
    }

    @Override
    protected Group fillEntity(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        if(resultSet != null) {
            group.setId(resultSet.getInt("GROUP_NUMBER"));
            group.setAvgMark(resultSet.getDouble("AVG_MARK"));
        }
        return group;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Group entity) throws SQLException {
        statement.setDouble(1, entity.getAvgMark());
    }
}
