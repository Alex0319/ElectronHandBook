package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.Study;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;
import by.iba.electronhandbook.exception.DaoException;

import java.sql.*;
import java.util.*;

import static by.iba.electronhandbook.constants.Constants.*;

public class StudyDaoImpl extends MySqlGenericDaoImpl<Study>{
    private static final String QUERY_PATTERN = "\\$\\$";

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

    @Override
    public List<Study> getAllCorrespondingToCondition(String queryName, String[] params) throws DaoException {
        String query = GET_MATCH_STUDIES;
        if(queryName.equals("query[]")){
            if( params.length > 1){
                StringBuilder stringBuilder = new StringBuilder(query).append(" AND NOT `STUDY_ID` IN (");
                for (int i = 1; i < params.length; i++){
                    stringBuilder.append("?,");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(')');
                query = stringBuilder.toString();
            }
        }
        return super.getAllCorrespondingToCondition(query, params);
    }

    @Override
    protected void addEntity(Connection connection, Study entity) throws SQLException {
        super.addEntity(connection, entity);
        entity.setId(getLastInsertedId());
        callChangeRelatedStudiesProcedure(connection, ADD_RELATED_ENTITIES, entity);
    }

    @Override
    protected void updateEntity(Connection connection, Study entity) throws SQLException {
        super.updateEntity(connection, entity);
        callChangeRelatedStudiesProcedure(connection, UPDATE_RELATED_ENTITIES, entity);
    }

    private void callChangeRelatedStudiesProcedure(Connection connection, String procedureCall, Study entity) throws SQLException{
        CallableStatement statement = null;
        try {
            statement = connection.prepareCall(procedureCall);
            statement.setInt(1, entity.getId());
            statement.setString(2, "STUDY_ID");
            statement.setString(3, "PROFESSOR_ID");
            statement.setString(4, "study_professor");
            statement.setString(5, getRelatedEntityIds(entity.getProfessors()));
            statement.execute();
        }finally {
            closeStatement(statement);
        }
    }
}
