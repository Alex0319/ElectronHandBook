package by.iba.electronhandbook.command.impl;

import by.iba.electronhandbook.exception.CommandException;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.factory.impl.ServiceMapper;
import by.iba.electronhandbook.service.GenericService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequiredDataCommand extends AbstarctJsonCommand{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        Map<String, String[]> params = req.getParameterMap();
        Map<String, List<?>> result = new HashMap<>();
        try{
            for(String param : params.keySet()){
                String serviceName = param.substring(0, param.length()-2);
                GenericService<?> service = ServiceMapper.getInstance().getService(serviceName);
                if(service != null) {
                    List<?> entities = service.getAllDto();
                    result.put(serviceName, entities);
                }
            }
            formJsonResponse(response, result);
        }catch (ServiceException e){
            throw new CommandException(e);
        }
    }
}
