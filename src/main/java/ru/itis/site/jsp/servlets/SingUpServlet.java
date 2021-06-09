package ru.itis.site.jsp.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.site.forms.SignUpForm;
import ru.itis.site.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SingUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // внутри сервлета получаем доступ к контексту сервлетов
        ServletContext servletContext = servletConfig.getServletContext();
        // получаем контекст спринга
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        // получаем бин
        this.signUpService = springContext.getBean(SignUpService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // компонент отвечающий за перенаправление запросов внутри сервера
        // здесь отправляем запрос на другой сервлет
        request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        SignUpForm form = SignUpForm.builder()
//                .firstName(request.getParameter("firstName"))
//                .lastName(request.getParameter("lastName"))
//                .email(request.getParameter("email"))
//                .password(request.getParameter("password"))
//                .build();


        // просто печать в консоль
        System.out.println(request.getAttribute("form"));

        // сохраним в БД нового пользователя
        signUpService.signUp((SignUpForm) request.getAttribute("form"));

        // после регистрации можно сделать редирект на страницу входа, signIn.jsp

        // так вручную можно сделать
//        response.setHeader("Location", "/signIn");
//        response.setStatus(302);

        // так более красиво
        response.sendRedirect("/signIn");
    }
}
