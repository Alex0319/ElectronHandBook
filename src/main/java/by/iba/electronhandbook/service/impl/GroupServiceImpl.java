package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.bean.Group;
import by.iba.electronhandbook.dao.impl.GroupDaoImpl;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AbstractService;

import java.util.List;
import java.util.Map;

public class GroupServiceImpl extends AbstractService<Group>{
    public GroupServiceImpl() {
        super(new GroupDaoImpl());
    }

    @Override
    protected Group buildEntity(Map<String, String[]> params) throws ServiceException {
        Group group = new Group();
        if(params.containsKey("id") && !params.get("id")[0].isEmpty()) {
            group.setId(Integer.parseInt(params.get("id")[0]));
        }
        if(params.containsKey("avgMark")){
            if(params.get("avgMark")[0].isEmpty()){
                group.setAvgMark(null);
            }else{
                group.setAvgMark(tryParseDouble(params.get("avgMark")[0]) ? Double.parseDouble(params.get("avgMark")[0]) : null);
            }
        }
        if(params.containsKey("prevId")){
            id = Integer.parseInt(params.get("prevId")[0]);
        }
        return group;
    }

    @Override
    public List<Group> getAllDto(String queryName, String[] params) throws ServiceException {
        List<Group> groups = getAll();
        for(Group group: groups){
            group.setAvgMark(null);
        }
        return groups;
    }
}
