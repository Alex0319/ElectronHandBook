package by.iba.electronhandbook.bean;

import java.util.Date;

public class Professor extends AbstractEntity{
    private String firstName;
    private String secondName;
    private String fatherName;
    private Date birthDate;
    private Double avgMark;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getAvgMark() {
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

        Professor student = (Professor) o;

        if (Double.compare(student.avgMark, avgMark) != 0){
            return false;
        }
        if (!firstName.equals(student.firstName)){
            return false;
        }
        if (!secondName.equals(student.secondName)){
            return false;
        }
        if (!fatherName.equals(student.fatherName)){
            return false;
        }
        return birthDate.equals(student.birthDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + fatherName.hashCode();
        result = 31 * result + birthDate.hashCode();
        temp = Double.doubleToLongBits(avgMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
