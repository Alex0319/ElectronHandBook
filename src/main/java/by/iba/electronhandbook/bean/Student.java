package by.iba.electronhandbook.bean;

import by.iba.electronhandbook.bean.dto.StudentDto;

public class Student extends StudentDto {
    private Double avgMark;
    private Group group;

    public Double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Student)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Student student = (Student) o;

        if (avgMark != null ? !avgMark.equals(student.avgMark) : student.avgMark != null){
            return false;
        }
        return group != null ? group.equals(student.group) : student.group == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (avgMark != null ? avgMark.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
}
