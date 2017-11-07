package by.iba.electronhandbook.bean;

import java.util.Date;

public class Mark extends AbstractEntity {
    private Study study;
    private Student student;
    private Date date;
    private Professor professor;
    private int mark;
    private String comments;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Mark)){
            return false;
        }

        Mark mark1 = (Mark) o;

        if (mark != mark1.mark){
            return false;
        }
        if (study != null ? !study.equals(mark1.study) : mark1.study != null){
            return false;
        }
        if (student != null ? !student.equals(mark1.student) : mark1.student != null){
            return false;
        }
        if (date != null ? !date.equals(mark1.date) : mark1.date != null){
            return false;
        }
        if (professor != null ? !professor.equals(mark1.professor) : mark1.professor != null){
            return false;
        }
        return comments != null ? comments.equals(mark1.comments) : mark1.comments == null;
    }

    @Override
    public int hashCode() {
        int result = study != null ? study.hashCode() : 0;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (professor != null ? professor.hashCode() : 0);
        result = 31 * result + mark;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
