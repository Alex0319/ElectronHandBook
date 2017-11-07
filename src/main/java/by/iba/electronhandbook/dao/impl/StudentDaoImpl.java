package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.Group;
import by.iba.electronhandbook.bean.Student;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.iba.electronhandbook.constants.Constants.*;

public class StudentDaoImpl extends MySqlGenericDaoImpl<Student> {
    public StudentDaoImpl(){
        super(GET_ALL_STUDENTS, GET_STUDENT, ADD_STUDENT, UPDATE_STUDENT, DELETE_STUDENT);
    }

    @Override
    protected Student fillEntity(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        Group group = new Group();
        student.setId(resultSet.getInt("ID"));
        student.setFirstName(resultSet.getString("FIRST_NAME"));
        student.setSecondName(resultSet.getString("SECOND_NAME"));
        student.setAvgMark(resultSet.getDouble("AVG_MARK"));
        group.setId(resultSet.getInt("GROUP_NUMBER"));
        group.setAvgMark(resultSet.getDouble("GROUP_AVG_MARK"));
        student.setGroup(group);
        return student;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Student entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getSecondName());
        statement.setDouble(3, entity.getAvgMark());
        statement.setInt(4, entity.getGroup().getId());
    }
}
