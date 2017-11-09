package by.iba.electronhandbook.bean;

import by.iba.electronhandbook.bean.dto.StudyDto;

public class Study extends StudyDto {
    private Integer hours;
    private Professor professor;
    private Double avgMark;

    public Integer getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
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
        if (!(o instanceof Study)) {
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Study study = (Study) o;

        if (hours != null ? !hours.equals(study.hours) : study.hours != null){
            return false;
        }
        if (professor != null ? !professor.equals(study.professor) : study.professor != null){
            return false;
        }
        return avgMark != null ? avgMark.equals(study.avgMark) : study.avgMark == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (professor != null ? professor.hashCode() : 0);
        result = 31 * result + (avgMark != null ? avgMark.hashCode() : 0);
        return result;
    }
}
