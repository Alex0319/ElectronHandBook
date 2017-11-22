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
        if(params.containsKey("id") && !params.get("id")[0].isEmpty()) {
            student.setId(Integer.parseInt(params.get("id")[0]));
        }
        if(params.containsKey("group")){
            Group group = new Group();
            group.setId(Integer.parseInt(params.get("group")[0]));
            student.setGroup(group);
        }
        if(params.containsKey("avgMark")) {
            student.setAvgMark(Double.parseDouble(params.get("avgMark")[0]));
        }
        if(params.containsKey("firstName")){
            student.setFirstName(params.get("firstName")[0]);
        }
        if(params.containsKey("secondName")){
            student.setSecondName(params.get("secondName")[0]);
        }
        if(params.containsKey("prevId")){
            id = Integer.parseInt(params.get("prevId")[0]);
        }
        return student;
    }

    @Override
    public List<StudentDto> getAllDto(String queryName, String[] params) throws ServiceException {
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
