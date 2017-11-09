package by.iba.electronhandbook.command.impl;

import by.iba.electronhandbook.command.Command;
import by.iba.electronhandbook.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        return true;
    }
}
