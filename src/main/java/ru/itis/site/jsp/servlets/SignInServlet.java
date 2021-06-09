package ru.itis.site.jsp.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.site.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // внутри сервлета получаем доступ к контексту сервлетов
        ServletContext servletContext = servletConfig.getServletContext();
        // получаем контекст спринга
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        // получаем бин
        this.signInService = springContext.getBean(SignInService.class);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (signInService.isCorrectCredentials(email, password)) {
            System.out.println("Password and Email is Correct, finally!");
            // создаём сессию, если пользователь корректный, если была, то пересоздаём, она хранится в RAM
            HttpSession session = request.getSession(true);
            // положим в эту сессию атрибут authenticated
            session.setAttribute("authenticated", true);
            // т.к. юзер аутентифицирован то кидаем его на /users
            response.sendRedirect("/users_jsp");
        } else {
            System.out.println("Credits is wrong !!! Try again...");
            // т.к. пароль логин не валидный, то редиректим снова на страницу логин/пароль
            response.sendRedirect("/signIn");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // на запрос GET возвращается содержимое signIn.jsp
        request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
    }
}
