package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.*;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;

import java.sql.Date;
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
        Student student = new Student();
        Professor professor = new Professor();

        mark.setId(resultSet.getInt("MARK_ID"));
        mark.setDate(resultSet.getDate("DATE"));
        mark.setMark(resultSet.getInt("MARK"));
        mark.setComments(resultSet.getString("COMMENTS"));

        study.setId(resultSet.getInt("STUDY_ID"));
        study.setName(resultSet.getString("NAME"));

        professor.setId(resultSet.getInt("PROFESSOR_ID"));
        professor.setFirstName(resultSet.getString("PROFESSOR_FIRST_NAME"));
        professor.setSecondName(resultSet.getString("PROFESSOR_SECOND_NAME"));
        professor.setFatherName(resultSet.getString("FATHER_NAME"));

        student.setId(resultSet.getInt("STUDENT_ID"));
        student.setFirstName(resultSet.getString("FIRST_NAME"));
        student.setSecondName(resultSet.getString("SECOND_NAME"));

        mark.setStudy(study);
        mark.setProfessor(professor);
        mark.setStudent(student);
        return mark;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Mark entity) throws SQLException {
        statement.setInt(1, entity.getStudy().getId());
        statement.setInt(2, entity.getStudent().getId());
        statement.setDate(3, new Date(entity.getDate().getTime()));
        statement.setInt(4, entity.getProfessor().getId());
        statement.setInt(5, entity.getMark());
        statement.setString(6, entity.getComments());
        statement.setInt(7, entity.getId());
    }
}
