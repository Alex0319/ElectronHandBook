package by.iba.electronhandbook.command.impl;

import by.iba.electronhandbook.command.Command;
import by.iba.electronhandbook.exception.CommandException;
import by.iba.electronhandbook.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractJsonCommand implements Command {
    public abstract boolean execute(HttpServletRequest req, HttpServletResponse response) throws CommandException;

    protected void formJsonResponse(HttpServletResponse resp, Object result) throws ServiceException{
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            resp.getWriter().write(objectMapper.writeValueAsString(result));
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }
}