package by.iba.electronhandbook.bean;

public class Group extends AbstractEntity{
    private Double avgMark;

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
        if (!(o instanceof Group)) {
            return false;
        }
        Group group = (Group) o;
        return Double.compare(group.avgMark, avgMark) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(avgMark);
        return (int) (temp ^ (temp >>> 32));
    }
}
