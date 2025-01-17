package chapter14;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = { "/chapter14/transaction" })
public class Transaction extends HttpServlet
{

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		Page.header(out);
		try
		{
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup(
					"java:/comp/env/jdbc/book"
			);
			Connection con = ds.getConnection();

			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));

			con.setAutoCommit(false);

			PreparedStatement st = con.prepareStatement(
					"insert into product(name, price) values(?, ?)"
			);
			st.setString(1, name);
			st.setInt(2, price);
			st.executeUpdate();

			st = con.prepareStatement(
					"select * from product where name=?"
			);
			st.setString(1, name);

			ResultSet rs = st.executeQuery();

			int line = 0;
			while (rs.next())
			{
				line++;
			}

			if (StringUtils.isEmpty(name))
			{
				con.rollback();
				out.println("商品名が不明なため、登録できません。");
			}
			else if (line != 1)
			{
				con.rollback();
				out.println("商品は既に登録されています。");
			}
			else
			{
				con.commit();
				out.println("商品を登録しました。");
			}

			con.setAutoCommit(true);

			st.close();
			con.close();

		}
		catch (Exception e)
		{
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
