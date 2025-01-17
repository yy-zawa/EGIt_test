package chapter3;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/chapter3/Hello" })
public class Hello extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 日本語対応
		response.setContentType("text/plain; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("Hello!");
		out.println("日本語だよ～");
		out.println(new java.util.Date());
	}
}