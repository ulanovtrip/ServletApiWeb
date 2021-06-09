package ru.itis.site.cookie.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profileVer2")
public class ProfileServletVerTwo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // чтобы можно было менять цвет, нужно вытаскивать его из параметра color:
        // для этого достанем его из запроса (request), так узнаем какой будет цвет, это приходит из браузера
        String color = request.getParameter("color");

        String colorStyle = "style=\"color:";

        // если цвет не передан
        if (color == null) {

            // получим объект cookies
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                // пройдёмся по всем cookie
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("color")) {
                        // вытащим цвет из куки и сохраним
                        color = cookie.getValue();
                    }
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
            response.addCookie(new Cookie("color", color));
        }

        // достаём цвет
        colorStyle = colorStyle + color + "\"";

        // отдаем в ответ готовую строку с цветом
        response.getWriter().println("<h1 " + colorStyle + ">Rats!</h1>");
    }
}
