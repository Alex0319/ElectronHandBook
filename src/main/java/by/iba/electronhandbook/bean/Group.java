package by.iba.electronhandbook.bean;

public class Group extends AbstractEntity{
    private Double avgMark;

    public Double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(Double avgMark) {
        this.avgMark = avgMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Group group = (Group) o;

        return avgMark != null ? avgMark.equals(group.avgMark) : group.avgMark == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (avgMark != null ? avgMark.hashCode() : 0);
        return result;
    }
}
