package by.iba.electronhandbook.bean.dto;

import by.iba.electronhandbook.bean.AbstractEntity;

public class UserDto extends AbstractEntity {
    protected String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDto)) {
            return false;
        }

        UserDto userDto = (UserDto) o;

        return role != null ? role.equals(userDto.role) : userDto.role == null;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }
}
