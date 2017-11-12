package by.iba.electronhandbook.bean;

import by.iba.electronhandbook.bean.dto.ProfessorDto;

import java.util.Date;
import java.util.Set;

public class Professor extends ProfessorDto {
    private Date birthDate;
    private Set<Study> studies;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Professor)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Professor professor = (Professor) o;

        if (birthDate != null ? !birthDate.equals(professor.birthDate) : professor.birthDate != null) return false;
        return studies != null ? studies.equals(professor.studies) : professor.studies == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (studies != null ? studies.hashCode() : 0);
        return result;
    }
}
