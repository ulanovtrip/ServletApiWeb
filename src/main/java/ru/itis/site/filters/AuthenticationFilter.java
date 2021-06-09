package ru.itis.site.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// задача этого фильтра проверить имеет ли доступ юзер к ресурсам, обрабатывает все запросы
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразование запроса и ответа
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // теперь нужно убедиться, что текущий пользователь аутентифицирован

        // если в запросе содержится запрос на /users
        if (request.getRequestURI().contains("users_jsp")) {
            // достанем сессию, false - означает, что если её не было, то не нужно её создавать
            HttpSession session = request.getSession(false);
            // если сессия не пустая
            if (session != null) {
                // и содержит атрибут authenticated
                if (session.getAttribute("authenticated") != null) {
                    // тогда пропускаем запрос далее
                    filterChain.doFilter(request, response);
                } else {
                    System.out.println("Отсутствует атрибут !");
                }
            } else {
                System.out.println("Отсутствует сессия !");
            }
        } else {
            // если запрос не относится к /users
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
