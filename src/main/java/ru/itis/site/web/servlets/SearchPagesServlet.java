package ru.itis.site.web.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/search_page")
public class SearchPagesServlet extends HttpServlet {

    private static String htmlForm = "<form action=\"http://localhost:8080/admin/search\">\n" +
            "\t<input type=\"text\" name=\"query\">\n" +
            "\t<br>\n" +
            "\t<input type=\"submit\" name=\"Search\">\n" +
            "</form>";

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // чтобы браузер понял, что это html
        response.setContentType("text/html");
        // отдал форму html для отображения в браузере
        response.getWriter().println(htmlForm);
    }
}
