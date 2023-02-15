package controller;

import controller.command.FrontCommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class Controller that works via Command pattern
 * it extends HttpServlet class
 * doGet/doPost request, sends it to the private method
 * and returns the JSP page
 */
@WebServlet(name = "FrontController", urlPatterns = {"/"})
public class FrontController extends HttpServlet {
/*    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format(
                    "com.baeldung.enterprise.patterns.front." + "controller.commands.%sCommand",
                    request.getParameter("command")));
            return (FrontCommand) type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }*/
}
