package by.iba.electronhandbook.bean.dto;

import by.iba.electronhandbook.bean.AbstractEntity;

public class ProfessorDto extends AbstractEntity {
    private String firstName;
    private String secondName;
    private String fatherName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof ProfessorDto)){
            return false;
        }

        ProfessorDto that = (ProfessorDto) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null){
            return false;
        }
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null){
            return false;
        }
        return fatherName != null ? fatherName.equals(that.fatherName) : that.fatherName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (fatherName != null ? fatherName.hashCode() : 0);
        return result;
    }
}
