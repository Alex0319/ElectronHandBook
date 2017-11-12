package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.Group;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
            Double value = resultSet.getDouble("AVG_MARK");
            group.setAvgMark(!resultSet.wasNull() ? value : null);
        }
        return group;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Group entity) throws SQLException {
        if(entity.getAvgMark() == null){
            statement.setNull(1, Types.DECIMAL);
        }else{
            statement.setDouble(1, entity.getAvgMark());
        }
        statement.setInt(2, entity.getId());
    }
}
