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
import java.util.List;
import java.util.Map;

public class MarkServiceImpl extends AbstractService<Mark>{
    public MarkServiceImpl() {
        super(new MarkDaoImpl());
    }

    @Override
    protected Mark buildEntity(Map<String, String[]> params) throws ServiceException {
        Mark mark = new Mark();
        try {
            if(params.containsKey("id") && !params.get("id")[0].isEmpty()) {
                mark.setId(Integer.parseInt(params.get("id")[0]));
            }
            if(params.containsKey("date")){
                mark.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("date")[0]));
            }
            if(params.containsKey("mark")) {
                mark.setMark(Integer.parseInt(params.get("mark")[0]));
            }
            if(params.containsKey("comments")){
                mark.setComments(params.get("comments")[0]);
            }
            if(params.containsKey("study")){
                Study study = new Study();
                study.setId(Integer.parseInt(params.get("study")[0]));
                mark.setStudy(study);
            }
            if(params.containsKey("student")){
                Student student = new Student();
                student.setId(Integer.parseInt(params.get("student")[0]));
                mark.setStudent(student);
            }
            if(params.containsKey("professor")){
                Professor professor = new Professor();
                professor.setId(Integer.parseInt(params.get("professor")[0]));
                mark.setProfessor(professor);
            }
            if(params.containsKey("prevId")){
                id = Integer.parseInt(params.get("prevId")[0]);
            }
        }catch (ParseException e){
            throw new ServiceException(e);
        }
        return mark;
    }

    @Override
    public List<Mark> getAllDto() throws ServiceException {
        return getAll();
    }
}
