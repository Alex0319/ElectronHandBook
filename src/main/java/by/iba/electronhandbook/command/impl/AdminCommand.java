package by.iba.electronhandbook.command.impl;

import by.iba.electronhandbook.command.Command;
import by.iba.electronhandbook.exception.CommandException;
import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AdminService;
import by.iba.electronhandbook.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        try{
            AdminService adminService = new AdminServiceImpl();
            adminService.redirectToMainAdminPage(req, response);
        }catch (ServiceException e){
            throw new CommandException(e);
        }
    }
}
