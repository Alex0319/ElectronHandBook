package by.iba.electronhandbook.servlet;

import by.iba.electronhandbook.command.Command;
import by.iba.electronhandbook.exception.CommandException;
import by.iba.electronhandbook.factory.impl.CommandMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main/*")
public class MainServlet extends HttpServlet{
    private static final Logger logger = LogManager.getLogger(MainServlet.class.getName());

    protected void doRequest(HttpServletRequest req, HttpServletResponse resp){
        try{
            String[] path = req.getPathInfo().split("/");
            Command command = CommandMapper.getInstance().getCommand(path[1]);
            if (command != null) {
                if(!command.execute(req, resp)){
                    req.getRequestDispatcher("/templates/pages/error/serverError.html").forward(req, resp);
                }
            } else {
                req.getRequestDispatcher("/templates/pages/error/notFoundError.html").forward(req, resp);
            }
        }catch (CommandException | ServletException | IOException e){
            logger.error(e);
        }
    }

    protected void doGet(HttpServletRequest req,HttpServletResponse resp) {
        doRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse resp) {
        doRequest(req, resp);
    }

    protected void doDelete(HttpServletRequest req,HttpServletResponse resp) {
        doRequest(req, resp);
    }
}
