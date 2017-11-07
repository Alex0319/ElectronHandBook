package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.Group;
import by.iba.electronhandbook.dao.impl.GroupDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.util.Map;

public class GroupServiceImpl extends AbstractService<Group>{
    public GroupServiceImpl() {
        super(new GroupDaoImpl());
    }

    @Override
    protected Group buildEntity(Map<String, String[]> params) throws ServiceException {
        Group group = new Group();
        if(params.containsKey("ID")) {
            group.setId(Integer.parseInt(params.get("ID")[0]));
        }
        if(params.containsKey("AVGMARK")){
            group.setAvgMark(Double.parseDouble(params.get("MARK")[0]));
        }
        if(params.containsKey("PREV_ID")){
            id = Integer.parseInt(params.get("PREV_ID")[0]);
        }
        return group;
    }
}
