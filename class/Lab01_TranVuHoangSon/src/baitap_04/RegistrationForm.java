package baitap_04;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/form"})
public class RegistrationForm extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PrintWriter writer = resp.getWriter();
		
		String html = "<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <title>Registration Form</title>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body style=\"background-color: gray; color: white;\">\r\n" + 
				"    <h1>Register</h1>\r\n" + 
				"    <form method=\"POST\">\r\n" + 
				"        <div>\r\n" + 
				"            <label>Name</label>\r\n" + 
				"            <br>\r\n" + 
				"            <input type=\"text\" placeholder=\"first name\" name=\"firstName\">\r\n" + 
				"            <input type=\"text\" placeholder=\"last name\" name=\"lastName\">\r\n" + 
				"        </div>\r\n" + 
				"        <br>\r\n" + 
				"        <div>\r\n" + 
				"            <label>User name</label>\r\n" + 
				"            <br>\r\n" + 
				"            <input type=\"text\" name=\"userName\">\r\n" + 
				"        </div>\r\n" + 
				"        <br>\r\n" + 
				"        <div>\r\n" + 
				"            <label>Email</label>\r\n" + 
				"            <br>\r\n" + 
				"            <input type=\"text\" name=\"email\">\r\n" + 
				"        </div>\r\n" + 
				"        <br>\r\n" + 
				"        <div>\r\n" + 
				"            <label>Password</label>\r\n" + 
				"            <br>\r\n" + 
				"            <input type=\"password\" name=\"password\">\r\n" + 
				"        </div>\r\n" + 
				"        <br>\r\n" + 
				"        <div>\r\n" + 
				"            <label>Facebook</label>\r\n" + 
				"            <br>\r\n" + 
				"            <input type=\"text\" name=\"facebook\" placeholder=\"Enter your fb profile URL\">            \r\n" + 
				"        </div>\r\n" + 
				"        <br>\r\n" + 
				"        <div>\r\n" + 
				"            <label>Short Bio</label>\r\n" + 
				"            <br>\r\n" + 
				"            <textarea name=\"shortBio\" cols=\"70\" rows=\"8\" placeholder=\"Share a little information about yourselt.\"></textarea>\r\n" + 
				"        </div>\r\n" + 
				"        <br>\r\n" + 
				"        <div>\r\n" + 
				"            <input type=\"submit\">\r\n" + 
				"        </div>\r\n" + 
				"    </form>\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>";
		writer.println(html);
		writer.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		String name = req.getParameter("firstName")+" "+req.getParameter("lastName");
		String email = req.getParameter("email");
		String facebook = req.getParameter("facebook");
		String shortbio = req.getParameter("shortBio");
		String html="<br>" + "<html>" 
				+ "<head>" + "<title>Result Page</title>" 
				+ "</head>" + "<body>" 
				+ "First Name: "+ name +"<br>" 
				+ "Email: "+ email +"<br> Facebook URL: "+ facebook +"<br>" 
				+ "Short Bio: "+ shortbio +"<br>" 
				+ "</body>" + "</html>";
		writer.println(html);
		writer.close();
	}
}
