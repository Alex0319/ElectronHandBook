package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.dto.ProfessorDto;
import by.iba.electronhandbook.dao.impl.ProfessorDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProfessorServiceImpl extends AbstractService<Professor> {
    public ProfessorServiceImpl() {
        super(new ProfessorDaoImpl());
    }

    @Override
    protected Professor buildEntity(Map<String, String[]> params) throws ServiceException {
        Professor professor = new Professor();
        try {
            if(params.containsKey("ID")) {
                professor.setId(Integer.parseInt(params.get("ID")[0]));
            }
            if(params.containsKey("FIRSTNAME")){
                professor.setFirstName(params.get("FIRSTNAME")[0]);
            }
            if(params.containsKey("SECONDNAME")){
                professor.setSecondName(params.get("SECONDNAME")[0]);
            }
            if(params.containsKey("FATHERNAME")){
                professor.setFatherName(params.get("FATHERNAME")[0]);
            }
            if(params.containsKey("BIRTHDATE")){
                professor.setBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("BIRTHDATE")[0]).getTime()));
            }
            if(params.containsKey("AVGMARK")){
                professor.setAvgMark(Double.parseDouble(params.get("FATHERNAME")[0])    );
            }
            if(params.containsKey("PREV_ID")){
                id = Integer.parseInt(params.get("PREV_ID")[0]);
            }
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
        return professor;
    }

    @Override
    public List<?> getAllDto() throws ServiceException {
        List<Professor> professors = getAll();
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
