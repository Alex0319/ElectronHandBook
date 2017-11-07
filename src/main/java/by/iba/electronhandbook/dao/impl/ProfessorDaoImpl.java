package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.iba.electronhandbook.constants.Constants.*;

public class ProfessorDaoImpl extends MySqlGenericDaoImpl<Professor>{
    public ProfessorDaoImpl() {
        super(GET_ALL_PROFESSORS, GET_PROFESSOR, ADD_PROFESSOR, UPDATE_PROFESSOR, DELETE_PROFESSOR);
    }

    @Override
    protected Professor fillEntity(ResultSet resultSet) throws SQLException {
        Professor professor = new Professor();
        if(resultSet != null){
            professor.setId(resultSet.getInt("ID"));
            professor.setFirstName(resultSet.getString("FIRST_NAME"));
            professor.setSecondName(resultSet.getString("SECOND_NAME"));
            professor.setFatherName(resultSet.getString("FATHER_NAME"));
            professor.setBirthDate(resultSet.getDate("BIRTH_DATE"));
            professor.setAvgMark(resultSet.getDouble("AVG_MARK"));
        }
        return professor;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Professor entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getSecondName());
        statement.setString(3, entity.getFatherName());
        statement.setDate(4, new Date(entity.getBirthDate().getTime()));
        statement.setDouble(5, entity.getAvgMark());
    }
}
