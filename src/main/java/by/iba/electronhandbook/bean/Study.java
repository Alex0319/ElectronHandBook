package by.iba.electronhandbook.bean;

import by.iba.electronhandbook.bean.dto.StudyDto;

import java.util.Set;

public class Study extends StudyDto {
    private Integer hours;
    private Double avgMark;
    private Set<Professor> professors;

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

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Study)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Study study = (Study) o;

        if (hours != null ? !hours.equals(study.hours) : study.hours != null){
            return false;
        }
        if (avgMark != null ? !avgMark.equals(study.avgMark) : study.avgMark != null){
            return false;
        }
        return professors != null ? professors.equals(study.professors) : study.professors == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (avgMark != null ? avgMark.hashCode() : 0);
        result = 31 * result + (professors != null ? professors.hashCode() : 0);
        return result;
    }
}
