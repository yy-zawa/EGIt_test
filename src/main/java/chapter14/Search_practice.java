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

@WebServlet(urlPatterns = { "/chapter14/search_practice" })
public class Search_practice extends HttpServlet
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
			String keyword_name = request.getParameter("keyword_name");
			String keyword_price = request.getParameter("keyword_price");

			// SQL文の作成
			PreparedStatement st_name = con.prepareStatement(
					"select * from product where name like ?");
			PreparedStatement st_price = con.prepareStatement(
					"select * from product where price like ?");
			
			// 商品名の検索ワードが入力されている場合
			if ( !(StringUtils.isEmpty(keyword_name)) )
			{
				st_name.setString(1, "%" + keyword_name + "%");
				
				// SQLの実行
				ResultSet rs = st_name.executeQuery();
				
				while (rs.next())
				{
					out.println(rs.getInt("id"));
					out.println("：");
					out.println(rs.getString("name"));
					out.println("：");
					out.println(rs.getInt("price"));
					out.println("<br>");
				}

				// SQL文作成オブジェクトのクローズ
				st_name.close();
				
			}
			
			// 金額の検索ワードが入力されている場合
			if( !(StringUtils.isEmpty(keyword_price)) )
			{
				st_price.setString(1, "%" + keyword_price + "%");
				
				// SQLの実行
				ResultSet rs = st_price.executeQuery();
				
				while (rs.next())
				{
					out.println(rs.getInt("id"));
					out.println("：");
					out.println(rs.getString("name"));
					out.println("：");
					out.println(rs.getInt("price"));
					out.println("<br>");
				}

				// SQL文作成オブジェクトのクローズ
				st_name.close();
			}

			// DB切断
			con.close();

		}
		catch (Exception e)
		{
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
