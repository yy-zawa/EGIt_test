package chapter17;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Page;

@WebServlet(urlPatterns={"/chapter17/count"})
public class Count extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);

		HttpSession session=request.getSession();

		Integer count=(Integer)session.getAttribute("count");
		if (count==null) count=0;
		count++;
		session.setAttribute("count", count);

		out.println("<p>"+"カウンター："+count+"</p>");
		out.println("<p>"+"セッションID："+session.getId()+"</p>");
		Page.footer(out);
	}
}
