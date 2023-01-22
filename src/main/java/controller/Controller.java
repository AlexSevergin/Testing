package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class Controller that works via Command pattern
 * it extends HttpServlet class
 * doGet/doPost request, sends it to the private method
 * and returns the JSP page
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        ActionFactory factory = ActionFactory.action();
        Action action = factory.getAction(req);
        String jsp = action.execute(req, resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);*/
    }
}