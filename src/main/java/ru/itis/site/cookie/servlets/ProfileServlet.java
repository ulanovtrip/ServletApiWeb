package ru.itis.site.cookie.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // чтобы можно было менять цвет, нужно вытаскивать его из параметра color:
        // для этого достанем его из запроса (request), так узнаем какой будет цвет, это приходит из браузера
        String color = request.getParameter("color");

        String colorStyle = "style=\"color:";

        // если цвет не передан
        if (color == null) {

            // проверим сначала, есть ли в cookie сохранённые цвета
            String cookiesHeader = request.getHeader("cookie");

            // разобъём их по пробелу
            String[] cookies = cookiesHeader.split("; ");

            // пройдёмся по всем cookie
            for (String cookie : cookies) {
                String[] current = cookie.split("=");
                if (current[0].equals("color")) {
                    color = current[1];
                }
            }

            // если всё еще нет цвета
            if (color == null) {
                // то цвет по умолчанию
                color = "black";
            }

        } else {
            // если какой-то цвето был передан, то нужно его запомнить
            // чтобы запомнить цвет, нужно использовать cookie
            response.setHeader("set-cookie", "color=" + color);
        }

        // достаём цвет
        colorStyle = colorStyle + color + "\"";

        // отдаем в ответ готовую строку с цветом
        response.getWriter().println("<h1 " + colorStyle + ">Rats!</h1>");
    }
}
