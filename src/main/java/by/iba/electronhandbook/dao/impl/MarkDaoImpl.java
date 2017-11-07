package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.*;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.iba.electronhandbook.constants.Constants.*;

public class MarkDaoImpl extends MySqlGenericDaoImpl<Mark>{
    public MarkDaoImpl() {
        super(GET_ALL_MARKS, GET_MARK, ADD_MARK, UPDATE_MARK, DELETE_MARK);
    }

    @Override
    protected Mark fillEntity(ResultSet resultSet) throws SQLException {
        Mark mark = new Mark();
        Study study = new Study();
        Professor professor = new Professor();
        Student student = new Student();
        Group group = new Group();
        mark.setId(resultSet.getInt("MARK_ID"));
        mark.setDate(resultSet.getDate("DATE"));
        mark.setMark(resultSet.getInt("MARK"));
        mark.setComments(resultSet.getString("COMMENTS"));

        study.setId(resultSet.getInt("STUDY_ID"));
        study.setName(resultSet.getString("NAME"));
        study.setHours(resultSet.getInt("HOURS"));
        study.setAvgMark(resultSet.getDouble("STUDY_AVG_MARK"));

        professor.setId(resultSet.getInt("PROFESSOR_ID"));
        professor.setFirstName(resultSet.getString("PROFESSOR_FIRST_NAME"));
        professor.setSecondName(resultSet.getString("PROFESSOR_SECOND_NAME"));
        professor.setFatherName(resultSet.getString("FATHER_NAME"));
        professor.setBirthDate(resultSet.getDate("BIRTH_DATE"));
        professor.setAvgMark(resultSet.getDouble("PROFESSOR_AVG_MARK"));

        student.setId(resultSet.getInt("STUDENT_ID"));
        student.setFirstName(resultSet.getString("FIRST_NAME"));
        student.setSecondName(resultSet.getString("SECOND_NAME"));
        student.setAvgMark(resultSet.getDouble("STUDENT_AVG_MARK"));
        group.setId(resultSet.getInt("GROUP_NUMBER"));
        group.setAvgMark(resultSet.getDouble("GROUP_AVG_MARK"));
        student.setGroup(group);

        study.setProfessor(professor);
        mark.setStudy(study);
        mark.setProfessor(professor);
        mark.setStudent(student);
        return mark;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Mark entity) throws SQLException {

    }
}
