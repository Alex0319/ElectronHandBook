package by.iba.electronhandbook.command;

import by.iba.electronhandbook.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void execute(HttpServletRequest req, HttpServletResponse response) throws CommandException;
}
