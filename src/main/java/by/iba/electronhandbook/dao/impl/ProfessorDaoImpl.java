package by.iba.electronhandbook.dao.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.Study;
import by.iba.electronhandbook.dao.MySqlGenericDaoImpl;
import by.iba.electronhandbook.dao.ProfessorDao;
import by.iba.electronhandbook.exception.DaoException;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static by.iba.electronhandbook.constants.Constants.*;

public class ProfessorDaoImpl extends MySqlGenericDaoImpl<Professor> implements ProfessorDao{
    private Map<Integer, Set<Study>> map = new HashMap<>();

    public ProfessorDaoImpl() {
        super(GET_ALL_PROFESSORS, GET_PROFESSOR, ADD_PROFESSOR, UPDATE_PROFESSOR, DELETE_PROFESSOR);
    }

    @Override
    public List<Professor> getAll() throws DaoException {
        map.clear();
        Set<Professor> professors = new LinkedHashSet<>(super.getAll());
        for (Professor professor: professors) {
            if(map.containsKey(professor.getId())){
                professor.setStudies(map.get(professor.getId()));
            }
        }
        return new ArrayList<>(professors);
    }

    @Override
    protected Professor fillEntity(ResultSet resultSet) throws SQLException {
        Professor professor = new Professor();
        Study study = new Study();
        if(resultSet != null){
            professor.setId(resultSet.getInt("ID"));
            professor.setFirstName(resultSet.getString("FIRST_NAME"));
            professor.setSecondName(resultSet.getString("SECOND_NAME"));
            professor.setFatherName(resultSet.getString("FATHER_NAME"));

            if(isColumnExistsInResultSet(resultSet, "BIRTH_DATE")){
                professor.setBirthDate(resultSet.getDate("BIRTH_DATE"));
            }

            if(isColumnExistsInResultSet(resultSet, "STUDY_ID")){
                study.setId(resultSet.getInt("STUDY_ID"));
                if(!resultSet.wasNull()){
                    study.setName(resultSet.getString("NAME"));
                    if(!map.containsKey(professor.getId())){
                        map.put(professor.getId(), new LinkedHashSet<Study>());
                    }
                    map.get(professor.getId()).add(study);
                }
            }
        }
        return professor;
    }

    @Override
    protected void fillStatement(PreparedStatement statement, Professor entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getSecondName());
        statement.setString(3, entity.getFatherName());
        statement.setDate(4, new Date(entity.getBirthDate().getTime()), Calendar.getInstance(TimeZone.getDefault()));
        if(statement.getParameterMetaData().getParameterCount() > 4){
            statement.setInt(5, entity.getId());
        }
    }

    @Override
    public List<Professor> getAllCorrespondingToCondition(String queryName, String[] params) throws DaoException {
        String query = GET_MATCH_PROFESSORS;
        if(queryName.equals("query[]") && params.length > 1){
            StringBuilder stringBuilder = new StringBuilder(query).append(" AND NOT `ID` IN (");
            for (int i = 1; i < params.length; i++){
                stringBuilder.append("?,");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(')');
            query = stringBuilder.toString();
        }
        return super.getAllCorrespondingToCondition(query ,params);
    }

    @Override
    protected void addEntity(Connection connection, Professor entity) throws SQLException {
        super.addEntity(connection, entity);
        entity.setId(getLastInsertedId());
        callChangeRelatedStudiesProcedure(connection, ADD_RELATED_ENTITIES, entity);
    }

    @Override
    protected void updateEntity(Connection connection, Professor entity) throws SQLException {
        super.updateEntity(connection, entity);
        callChangeRelatedStudiesProcedure(connection, UPDATE_RELATED_ENTITIES, entity);
    }

    private void callChangeRelatedStudiesProcedure(Connection connection, String procedureCall, Professor entity) throws SQLException{
        CallableStatement statement = null;
        try {
            statement = connection.prepareCall(procedureCall);
            statement.setInt(1, entity.getId());
            statement.setString(2, "PROFESSOR_ID");
            statement.setString(3, "STUDY_ID");
            statement.setString(4, "study_professor");

            String relatedEntityIds = getRelatedEntityIds(entity.getStudies());
            if(relatedEntityIds == null || relatedEntityIds.isEmpty()){
                statement.setNull(5, Types.VARCHAR);
            }else{
                statement.setString(5, relatedEntityIds);
            }

            statement.execute();
        }finally {
            closeStatement(statement);
        }
    }
}
