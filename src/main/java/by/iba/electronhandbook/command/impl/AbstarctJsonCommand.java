package by.iba.electronhandbook.command.impl;

import by.iba.electronhandbook.command.Command;
import by.iba.electronhandbook.exception.CommandException;
import by.iba.electronhandbook.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstarctJsonCommand implements Command {
    public abstract void execute(HttpServletRequest req, HttpServletResponse response) throws CommandException;

    protected void formJsonResponse(HttpServletResponse resp, Object result) throws ServiceException{
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Gson jsonConverter = new Gson();
            resp.getWriter().write(jsonConverter.toJson(result));
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }
}