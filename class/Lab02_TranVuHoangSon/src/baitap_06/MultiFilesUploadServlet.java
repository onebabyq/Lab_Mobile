package baitap_06;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = {"/multiFileUpload"})
public class MultiFilesUploadServlet extends HttpServlet {
	final String UPLOAD_DIRECTORY = "T:/upload";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		
		String html = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "<title>Upload Form</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<h1>Upload multi-files</h1>\r\n"
				+ "	<br>\r\n" + "	<form method=\"POST\" enctype=\"multipart/form-data\">\r\n" + "		<div>\r\n"
				+ "			<label for=\"chooseFile1\">File #1: </label> <input name=\"chooseFile1\"\r\n"
				+ "				type=\"file\" multiple=\"multiple\">\r\n" + "		</div>\r\n" + "		<br>\r\n"
				+ "		<div>\r\n"
				+ "			<label for=\"chooseFile2\">File #2: </label> <input name=\"chooseFile2\"\r\n"
				+ "				type=\"file\" multiple=\"multiple\">\r\n" + "		</div>\r\n" + "		<br>\r\n"
				+ "		<div>\r\n"
				+ "			<label for=\"chooseFile3\">File #3: </label> <input name=\"chooseFile3\"\r\n"
				+ "				type=\"file\" multiple=\"multiple\">\r\n" + "		</div>\r\n" + "		<br>\r\n"
				+ "		<div>\r\n"
				+ "			<label for=\"chooseFile4\">File #4: </label> <input name=\"chooseFile4\"\r\n"
				+ "				type=\"file\" multiple=\"multiple\">\r\n" + "		</div>\r\n" + "		<br>\r\n"
				+ "		<div>\r\n"
				+ "			<label for=\"chooseFile5\">File #5: </label> <input name=\"chooseFile5\"\r\n"
				+ "				type=\"file\" multiple=\"multiple\">\r\n" + "		</div>\r\n" + "		<br>\r\n"
				+ "		<div>\r\n"
				+ "			<input type=\"submit\" name=\"submit\" id=\"\"> <input type=\"reset\"\r\n"
				+ "				name=\"reset\">\r\n" + "		</div>\r\n" + "	</form>\r\n" + "</body>\r\n"
				+ "</html>";
		
		writer.print(html);
		writer.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		if(!ServletFileUpload.isMultipartContent(req)) {
			resp.getWriter().println("Does Not Support!");
		}
		else {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
				
				for(FileItem item : multiparts) {
					if(!item.isFormField()) {
						item.write(new File(UPLOAD_DIRECTORY+ File.separator + new File(item.getName()).getName()));
					}
				}
				
				resp.getWriter().println("<h1>Upload Successfully</h1>");
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.close();
	}

}
