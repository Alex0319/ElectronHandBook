package by.iba.electronhandbook.command.impl;

import by.iba.electronhandbook.exception.CommandException;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.factory.impl.ServiceMapper;
import by.iba.electronhandbook.service.GenericService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateEntityCommand extends AbstractJsonCommand {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        String[] path = req.getPathInfo().split("/");
        if(path.length <= 2){
            return false;
        }
        GenericService<?> service = ServiceMapper.getInstance().getService(path[2]);
        try{
            if(service != null) {
                formJsonResponse(response, service.update(req.getParameterMap()));
            }else{
                return false;
            }
        }catch (ServiceException e) {
            throw new CommandException(e);
        }
        return true;
    }
}
