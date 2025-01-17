package example05;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns={"/example05/search"})
public class search extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Page.header(out);
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/book");
            Connection con = ds.getConnection();

            String keyword = request.getParameter("keyword");
            String priceRange = request.getParameter("priceRange");

            // プライスレンジを分解
            String[] range = priceRange.split("-");
            int minPrice = Integer.parseInt(range[0]);
            int maxPrice = Integer.parseInt(range[1]);

            PreparedStatement st = con.prepareStatement(
                "SELECT * FROM product WHERE name LIKE ? AND price BETWEEN ? AND ?");
            st.setString(1, "%" + keyword + "%");
            st.setInt(2, minPrice);
            st.setInt(3, maxPrice);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                out.println(rs.getInt("id"));
                out.println("：");
                out.println(rs.getString("name"));
                out.println("：");
                out.println(rs.getInt("price"));
                out.println("<br>");
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
        Page.footer(out);
    }
}

