package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.Study;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;
import by.iba.electronhandbook.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static by.iba.electronhandbook.constants.Constants.*;

public class StudyDaoImpl extends MySqlGenericDaoImpl<Study>{
    private Map<Integer, Set<Professor>> map = new HashMap<>();

    public StudyDaoImpl() {
        super(GET_ALL_STUDIES, GET_STUDY, ADD_STUDY, UPDATE_STUDY, DELETE_STUDY);
    }

    @Override
    public List<Study> getAll() throws DaoException {
        map.clear();
        Set<Study> studies = new LinkedHashSet<>(super.getAll());
        for (Study study: studies) {
            if(map.containsKey(study.getId())){
                study.setProfessors(map.get(study.getId()));
            }
        }
        return new ArrayList<>(studies);
    }

    @Override
    protected Study fillEntity(ResultSet resultSet) throws SQLException {
        Study study = new Study();
        Professor professor = new Professor();
        if(resultSet != null){
            study.setId(resultSet.getInt("STUDY_ID"));
            study.setName(resultSet.getString("NAME"));
            study.setHours(resultSet.getInt("HOURS"));
            study.setAvgMark(resultSet.getDouble("STUDY_AVG_MARK"));
            professor.setId(resultSet.getInt("PROFESSOR_ID"));
            if(!resultSet.wasNull()){
                professor.setFirstName(resultSet.getString("FIRST_NAME"));
                professor.setSecondName(resultSet.getString("SECOND_NAME"));
                professor.setFatherName(resultSet.getString("FATHER_NAME"));
                if(!map.containsKey(study.getId())){
                    map.put(study.getId(), new HashSet<Professor>());
                }
                map.get(study.getId()).add(professor);
            }
        }
        return study;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Study entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getHours());
        statement.setDouble(3, entity.getAvgMark());
        if(statement.getParameterMetaData().getParameterCount() > 3){
            statement.setInt(4, entity.getId());
        }
    }
}
