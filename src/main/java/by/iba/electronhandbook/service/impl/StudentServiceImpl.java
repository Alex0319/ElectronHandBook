package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.Group;
import by.iba.electronhandbook.bean.Student;
import by.iba.electronhandbook.bean.dto.StudentDto;
import by.iba.electronhandbook.dao.impl.StudentDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl extends AbstractService<Student>{
    public StudentServiceImpl() {
        super(new StudentDaoImpl());
    }

    @Override
    protected Student buildEntity(Map<String, String[]> params) throws ServiceException {
        Student student = new Student();
        if(params.containsKey("ID")) {
            student.setId(Integer.parseInt(params.get("ID")[0]));
        }
        if(params.containsKey("GROUP")){
            Group group = new Group();
            group.setId(Integer.parseInt(params.get("GROUP")[0]));
            student.setGroup(group);
        }
        if(params.containsKey("AVGMARK")) {
            student.setAvgMark(Double.parseDouble(params.get("AVGMARK")[0]));
        }
        if(params.containsKey("FIRSTNAME")){
            student.setFirstName(params.get("FIRSTNAME")[0]);
        }
        if(params.containsKey("SECONDNAME")){
            student.setSecondName(params.get("SECONDNAME")[0]);
        }
        if(params.containsKey("PREV_ID")){
            id = Integer.parseInt(params.get("PREV_ID")[0]);
        }
        return student;
    }

    @Override
    public List<StudentDto> getAllDto() throws ServiceException {
        List<Student> students = getAll();
        List<StudentDto> studentDtos = new ArrayList<>();

        for(Student  student: students){
            StudentDto studentDto = new StudentDto();
            studentDto.setId(student.getId());
            studentDto.setFirstName(student.getFirstName());
            studentDto.setSecondName(student.getSecondName());
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }
}
