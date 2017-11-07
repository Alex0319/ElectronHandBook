package by.iba.electronhandbook.service;

import by.iba.electronhandbook.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminService {
    void redirectToMainAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
