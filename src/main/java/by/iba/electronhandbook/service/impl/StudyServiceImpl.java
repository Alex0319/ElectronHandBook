package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.Study;
import by.iba.electronhandbook.dao.impl.StudyDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.util.Map;

public class StudyServiceImpl extends AbstractService<Study>{
    public StudyServiceImpl() {
        super(new StudyDaoImpl());
    }

    @Override
    protected Study buildEntity(Map<String, String[]> params) throws ServiceException {
        Study study = new Study();
        if(params.containsKey("ID")) {
            study.setId(Integer.parseInt(params.get("ID")[0]));
        }
        if(params.containsKey("NAME")){
            study.setName(params.get("NAME")[0]);
        }
        if(params.containsKey("HOURS")){
            study.setHours(Integer.parseInt(params.get("NAME")[0]));
        }
        if(params.containsKey("AVGMARK")){
            study.setAvgMark(Double.parseDouble(params.get("AVGMARK")[0]));
        }
        if(params.containsKey("PROFESSOR")){
            Professor professor = new Professor();
            professor.setId(Integer.parseInt(params.get("PROFESSOR")[0]));
            study.setProfessor(professor);
        }
        if(params.containsKey("PREV_ID")){
            id = Integer.parseInt(params.get("PREV_ID")[0]);
        }
        return study;
    }
}
