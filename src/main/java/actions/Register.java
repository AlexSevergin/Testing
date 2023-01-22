package actions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("register/html");
        PrintWriter out = response.getWriter();
        String n=request.getParameter("userName");
        String p=request.getParameter("userPass");
        String e=request.getParameter("userEmail");
        String c=request.getParameter("userCountry");
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
            PreparedStatement ps=con.prepareStatement(
                    "insert into registeruser values(?,?,?,?)");
            ps.setString(1,n);
            ps.setString(2,p);
            ps.setString(3,e);
            ps.setString(4,c);
            int i=ps.executeUpdate();
            if(i>0)
                out.print("You are successfully registered...");
        } catch (Exception e2) {System.out.println(e2);}
        out.close();
    }
}