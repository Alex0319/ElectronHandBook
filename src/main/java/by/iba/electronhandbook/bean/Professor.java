package by.iba.electronhandbook.bean;

import by.iba.electronhandbook.bean.dto.ProfessorDto;

import java.util.Date;

public class Professor extends ProfessorDto {
    private Date birthDate;
    private Double avgMark;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Professor)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Professor professor = (Professor) o;

        if (birthDate != null ? !birthDate.equals(professor.birthDate) : professor.birthDate != null){
            return false;
        }
        return avgMark != null ? avgMark.equals(professor.avgMark) : professor.avgMark == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (avgMark != null ? avgMark.hashCode() : 0);
        return result;
    }
}
