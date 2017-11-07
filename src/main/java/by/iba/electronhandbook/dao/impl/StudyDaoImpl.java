package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.Study;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.iba.electronhandbook.constants.Constants.*;

public class StudyDaoImpl extends MySqlGenericDaoImpl<Study>{
    public StudyDaoImpl() {
        super(GET_ALL_STUDIES, GET_STUDY, ADD_STUDY, UPDATE_STUDY, DELETE_STUDY);
    }

    @Override
    protected Study fillEntity(ResultSet resultSet) throws SQLException {
        Study study = new Study();
        Professor professor = new Professor();
        study.setId(resultSet.getInt("ID"));
        study.setName(resultSet.getString("NAME"));
        study.setHours(resultSet.getInt("HOURS"));
        study.setAvgMark(resultSet.getDouble("STUDY_AVG_MARK"));
        professor.setId(resultSet.getInt("PROFESSOR_ID"));
        professor.setFirstName(resultSet.getString("FIRST_NAME"));
        professor.setSecondName(resultSet.getString("SECOND_NAME"));
        professor.setFatherName(resultSet.getString("FATHER_NAME"));
        professor.setBirthDate(resultSet.getDate("BIRTH_DATE"));
        professor.setAvgMark(resultSet.getDouble("PROFESSOR_AVG_MARK"));
        study.setProfessor(professor);
        return study;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Study entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getHours());
        statement.setInt(3, entity.getProfessor().getId());
        statement.setDouble(4, entity.getAvgMark());
    }
}
