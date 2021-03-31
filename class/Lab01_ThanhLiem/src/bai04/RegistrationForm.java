package bai04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form")
public class RegistrationForm extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String TITLE = "Registration Form";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter(); 
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
		writer.print(html);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("firstName") + " " + request.getParameter("lastName");
		String email = request.getParameter("email");
		String facebook = request.getParameter("facebook");
		String shortbio = request.getParameter("shortBio");
		String html = "<br>" + "<html>" + "<head>" + "<title>Result Page</title>" + "</head>" + "<body>"
				+ "Name: " + name + "<br>" + "Email: " + email + "<br> Facebook URL: " + facebook + "<br>"
				+ "Short Bio: " + shortbio + "<br>" + "</body>" + "</html>";
		out.println(html);
	}
}
