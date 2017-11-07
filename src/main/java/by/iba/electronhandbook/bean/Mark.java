package by.iba.electronhandbook.bean;

import java.util.Date;

public class Mark extends AbstractEntity {
    private Study study;
    private Student student;
    private Date date;
    private Professor professor;
    private int mark;
    private String comments;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mark)) {
            return false;
        }

        Mark mark1 = (Mark) o;

        if (mark != mark1.mark) {
            return false;
        }
        if (!study.equals(mark1.study)) {
            return false;
        }
        if (!student.equals(mark1.student)) {
            return false;
        }
        if (!date.equals(mark1.date)){
            return false;
        }
        if (!professor.equals(mark1.professor)){
            return false;
        }
        return comments.equals(mark1.comments);
    }

    @Override
    public int hashCode() {
        int result = study.hashCode();
        result = 31 * result + student.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + professor.hashCode();
        result = 31 * result + mark;
        result = 31 * result + comments.hashCode();
        return result;
    }
}
