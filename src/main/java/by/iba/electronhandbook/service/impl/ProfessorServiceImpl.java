package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.Study;
import by.iba.electronhandbook.bean.dto.ProfessorDto;
import by.iba.electronhandbook.dao.impl.ProfessorDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProfessorServiceImpl extends AbstractService<Professor> {
    public ProfessorServiceImpl() {
        super(new ProfessorDaoImpl());
    }

    @Override
    protected Professor buildEntity(Map<String, String[]> params) throws ServiceException {
        Professor professor = new Professor();
        try {
            if(params.containsKey("id") && !params.get("id")[0].isEmpty()) {
                professor.setId(Integer.parseInt(params.get("id")[0]));
            }
            if(params.containsKey("firstName")){
                professor.setFirstName(params.get("firstName")[0]);
            }
            if(params.containsKey("secondName")){
                professor.setSecondName(params.get("secondName")[0]);
            }
            if(params.containsKey("fatherName")){
                professor.setFatherName(params.get("fatherName")[0]);
            }
            if(params.containsKey("birthDate")){
                professor.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("birthDate")[0]));
            }
            if(params.containsKey("prevId")){
                id = Integer.parseInt(params.get("prevId")[0]);
            }
            if(params.containsKey("study")){
                professor.setStudies(getRelatedEntityIds(params.get("study"), Study.class));
            }
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
        return professor;
    }

    @Override
    public List<?> getAllDto(String queryName, String[] params) throws ServiceException {
        List<Professor> professors = params != null ? getMatches(queryName, params) : getAll();
        List<ProfessorDto> professorDtos = new ArrayList<>();

        for(Professor professor: professors){
            ProfessorDto professorDto = new ProfessorDto();
            professorDto.setId(professor.getId());
            professorDto.setFirstName(professor.getFirstName());
            professorDto.setSecondName(professor.getSecondName());
            professorDto.setFatherName(professor.getFatherName());
            professorDtos.add(professorDto);
        }
        return professorDtos;
    }
}
