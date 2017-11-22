package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.User;
import by.iba.electronhandbook.bean.dto.UserDto;
import by.iba.electronhandbook.dao.impl.UserDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl extends AbstractService<UserDto>{
    public UserServiceImpl(){
        super(new UserDaoImpl());
    }

    @Override
    public List<UserDto> getAll() throws ServiceException {
        List<UserDto> users = super.getAll();
        List<UserDto> userDtos = new ArrayList<>();

        for(UserDto user: users){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setRole(user.getRole());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public List<UserDto> getAllDto(String queryName, String[] params) throws ServiceException {
        return getAll();
    }

    @Override
    protected User buildEntity(Map<String, String[]> params) throws ServiceException {
        User user = new User();
        if(params.containsKey("id")     && !params.get("id")[0].isEmpty()) {
            user.setId(Integer.parseInt(params.get("id")[0]));
        }
        if(params.containsKey("role")){
            user.setRole(params.get("role")[0]);
        }
        if(params.containsKey("prevId")){
            id = Integer.parseInt(params.get("prevId")[0]);
        }
        return user;
    }
}
