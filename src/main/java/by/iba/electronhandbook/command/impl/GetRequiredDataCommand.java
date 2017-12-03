package by.iba.electronhandbook.command.impl;

import by.iba.electronhandbook.exception.CommandException;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.factory.impl.ServiceMapper;
import by.iba.electronhandbook.service.GenericService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequiredDataCommand extends AbstractJsonCommand {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        Map<String, String[]> params = req.getParameterMap();
        Map<String, List<?>> result = new HashMap<>();
        try{
            if(params.containsKey("service")) {
                for (String serviceName : params.get("service")) {
                    GenericService<?> service = ServiceMapper.getInstance().getService(serviceName);
                    if (service != null) {
                        List entities = new ArrayList<>();
                        if(params.size() > 1){
                            for (String key: params.keySet()){
                                if(!key.equals("service")){
                                    entities.addAll(service.getAllDto(key, params.get(key)));
                                }
                            }
                        }else{
                            entities = service.getAllDto(null, null);
                        }
                        result.put(serviceName, entities);
                    }
                }
            }
            formJsonResponse(response, result);
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return true;
    }
}
