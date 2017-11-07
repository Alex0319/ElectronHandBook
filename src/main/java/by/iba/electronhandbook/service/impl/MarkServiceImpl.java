package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.Mark;
import by.iba.electronhandbook.bean.Professor;
import by.iba.electronhandbook.bean.Student;
import by.iba.electronhandbook.bean.Study;
import by.iba.electronhandbook.dao.impl.MarkDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MarkServiceImpl extends AbstractService<Mark>{
    public MarkServiceImpl() {
        super(new MarkDaoImpl());
    }

    @Override
    protected Mark buildEntity(Map<String, String[]> params) throws ServiceException {
        Mark mark = new Mark();
        try {
            if(params.containsKey("ID")) {
                mark.setId(Integer.parseInt(params.get("ID")[0]));
            }
            if(params.containsKey("DATE")){
                mark.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("DATE")[0]).getTime()));
            }
            if(params.containsKey("MARK")) {
                mark.setMark(Integer.parseInt(params.get("AVGMARK")[0]));
            }
            if(params.containsKey("COMMENTS")){
                mark.setComments(params.get("COMMENTS")[0]);
            }
            if(params.containsKey("STUDY")){
                Study study = new Study();
                study.setId(Integer.parseInt(params.get("STUDY")[0]));
                mark.setStudy(study);
            }
            if(params.containsKey("STUDENT")){
                Student student = new Student();
                student.setId(Integer.parseInt(params.get("STUDENT")[0]));
                mark.setStudent(student);
            }
            if(params.containsKey("PROFESSOR")){
                Professor professor = new Professor();
                professor.setId(Integer.parseInt(params.get("PROFESSOR")[0]));
                mark.setProfessor(professor);
            }
            if(params.containsKey("PREV_ID")){
                id = Integer.parseInt(params.get("PREV_ID")[0]);
            }
        }catch (ParseException e){
            throw new ServiceException(e);
        }
        return mark;
    }
}
