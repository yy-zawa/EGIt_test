package example04;

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

@WebServlet(urlPatterns = { "/example04/fruit" })
public class fruit extends HttpServlet
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

			// DB接続
			Connection con = ds.getConnection();

			// クライアントからの入力受け付け
			String name = request.getParameter("name");
			//			String keyword_price = request.getParameter("keyword_price");

			// SQL文の作成
			PreparedStatement st_fruit = con.prepareStatement(
					"select * from test where fruit like ?"
			);
			//			PreparedStatement st_price = con.prepareStatement(
			//					"select * from product where price like ?");

			// 商品名の検索ワードが入力されている場合
			if (!(StringUtils.isEmpty(name)))
			{
				st_fruit.setString(1, "%" + name + "%");

				// SQLの実行
				ResultSet rs = st_fruit.executeQuery();

				while (rs.next())
				{
					out.println(rs.getString("fruit"));
				}

				// SQL文作成オブジェクトのクローズ
				st_fruit.close();

			}

			// 金額の検索ワードが入力されている場合
			//			if( !(StringUtils.isEmpty(keyword_price)) )
			//			{
			//				st_price.setString(1, "%" + keyword_price + "%");

			// SQLの実行
			//				ResultSet rs = st_price.executeQuery();
			//				
			//				while (rs.next())
			//				{
			//					out.println(rs.getInt("id"));
			//					out.println("：");
			//					out.println(rs.getString("name"));
			//					out.println("：");
			//					out.println(rs.getInt("price"));
			//					out.println("<br>");
			//				}

			// SQL文作成オブジェクトのクローズ
			//				st_name.close();
			//			}

			// DB切断
			//			con.close();

		}
		catch (Exception e)
		{
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
