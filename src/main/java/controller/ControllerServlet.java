package controller;

import controller.wrappers.RequestWrapper;
import controller.wrappers.ResponseWrapper;
import model.commands.general.CommandImpl;
import model.commands.general.ICommand;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lexy on 08.12.16.
 */
@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURL = request.getRequestURI();
        String command = requestURL.split("/")[2];

        ICommand commandFactory = CommandImpl.getInstance();
        commandFactory.execute(command, new RequestWrapper(request), new ResponseWrapper(response));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
