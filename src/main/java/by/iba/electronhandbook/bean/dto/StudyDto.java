package by.iba.electronhandbook.bean.dto;

import by.iba.electronhandbook.bean.AbstractEntity;

public class StudyDto extends AbstractEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudyDto)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        StudyDto studyDto = (StudyDto) o;

        return name != null ? name.equals(studyDto.name) : studyDto.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
