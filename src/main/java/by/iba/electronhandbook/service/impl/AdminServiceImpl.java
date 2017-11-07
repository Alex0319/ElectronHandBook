package by.iba.electronhandbook.service.impl;

import by.iba.electronhandbook.exception.ServiceException;
import by.iba.electronhandbook.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AdminServiceImpl implements AdminService{
    private final static Logger logger = LogManager.getLogger(AdminService.class.getName());
    private final static String TABLES_INFO = "tablesInfo.properties";

    @Override
    public void redirectToMainAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        InputStream inputStream =null;
        Properties properties=new Properties();
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(TABLES_INFO);
            properties.load(inputStream);
            request.setAttribute("tableNames", properties.values());
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        }catch (ServletException | IOException e){
            throw new ServiceException(e);
        }finally {
            try {
                if(inputStream !=null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }
}
