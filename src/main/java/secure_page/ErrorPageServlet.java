package secure_page;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/secure_page/error") // "/secure/error" にアクセスする
public class ErrorPageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		response.getWriter().println("不正ログインをしようとしています！");
	}
}
