package by.iba.electronhandbook.command.impl;

import by.iba.electronhandbook.exception.CommandException;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.factory.impl.ServiceMapper;
import by.iba.electronhandbook.service.GenericService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllEntitiesCommand extends AbstarctJsonCommand{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        String[] path = req.getPathInfo().split("/");
        GenericService<?> service = ServiceMapper.getInstance().getService(path[2]);
        try{
            if(service != null) {
                List<?> entities = service.getAll();
                formJsonResponse(response, entities);
            }
        }catch (ServiceException e){
            throw new CommandException(e);
        }
    }
}