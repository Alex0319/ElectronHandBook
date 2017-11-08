package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.Study;
import by.iba.electronhandbook.bean.dto.StudyDto;
import by.iba.electronhandbook.dao.impl.StudyDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudyServiceImpl extends AbstractService<Study>{
    public StudyServiceImpl() {
        super(new StudyDaoImpl());
    }

    @Override
    protected Study buildEntity(Map<String, String[]> params) throws ServiceException {
        Study study = new Study();
        if(params.containsKey("id") && !params.get("id")[0].isEmpty()) {
            study.setId(Integer.parseInt(params.get("id")[0]));
        }
        if(params.containsKey("name")){
            study.setName(params.get("name")[0]);
        }
        if(params.containsKey("hours")){
            study.setHours(Integer.parseInt(params.get("hours")[0]));
        }
        if(params.containsKey("avgMark")){
            study.setAvgMark(Double.parseDouble(params.get("avgMark")[0]));
        }
        if(params.containsKey("professor")){
            Professor professor = new Professor();
            professor.setId(Integer.parseInt(params.get("professor")[0]));
            study.setProfessor(professor);
        }
        if(params.containsKey("prevId")){
            id = Integer.parseInt(params.get("prevId")[0]);
        }
        return study;
    }

    @Override
    public List<StudyDto> getAllDto() throws ServiceException {
        List<Study> studies = getAll();
        List<StudyDto> studyDtos = new ArrayList<>();

        for(Study study: studies){
            StudyDto studyDto = new StudyDto();
            studyDto.setId(study.getId());
            studyDto.setName(study.getName());
            studyDtos.add(studyDto);
        }
        return studyDtos;
    }
}
