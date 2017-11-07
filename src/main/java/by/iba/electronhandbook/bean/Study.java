package by.iba.electronhandbook.bean;

public class Study extends AbstractEntity{
    private String name;
    private int hours;
    private Professor professor;
    private double avgMark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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
        if (!(o instanceof Study)) {
            return false;
        }

        Study study = (Study) o;

        if (hours != study.hours) {
            return false;
        }
        if (Double.compare(study.avgMark, avgMark) != 0) {
            return false;
        }
        if (name != null ? !name.equals(study.name) : study.name != null) {
            return false;
        }
        return professor != null ? professor.equals(study.professor) : study.professor == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + hours;
        result = 31 * result + (professor != null ? professor.hashCode() : 0);
        temp = Double.doubleToLongBits(avgMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
