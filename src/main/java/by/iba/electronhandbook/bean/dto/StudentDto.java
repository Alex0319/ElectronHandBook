package by.iba.electronhandbook.bean.dto;

import by.iba.electronhandbook.bean.AbstractEntity;

public class StudentDto extends AbstractEntity {
    private String firstName;
    private String secondName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof StudentDto)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        StudentDto that = (StudentDto) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null){
            return false;
        }
        return secondName != null ? secondName.equals(that.secondName) : that.secondName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        return result;
    }
}
