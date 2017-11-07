package by.iba.electronhandbook.bean;

public class Student extends AbstractEntity {
    private String firstName;
    private String secondName;
    private Double avgMark;
    private Group group;

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

    public double getAvgMark() {
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

        Student student = (Student) o;

        if (Double.compare(student.avgMark, avgMark) != 0){
            return false;
        }
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null){
            return false;
        }
        if (secondName != null ? !secondName.equals(student.secondName) : student.secondName != null){
            return false;
        }
        return group != null ? group.equals(student.group) : student.group == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        temp = Double.doubleToLongBits(avgMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
}
