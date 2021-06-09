package ru.itis.site.jsp.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.site.dto.AccountDto;
import ru.itis.site.services.AccountsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// users_jsp - это path на который реагирует сервлет
@WebServlet("/users_jsp")
public class UsersServletJsp extends HttpServlet {

    private AccountsService accountsService;

    // вызывается один раз для каждого сервлете
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // внутри сервлета получаем доступ к контексту сервлетов
        ServletContext servletContext = servletConfig.getServletContext();
        // получаем контекст спринга
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        // получаем бин
        this.accountsService = springContext.getBean(AccountsService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // вытащили всех юзеров из БД в виде dto
        List<AccountDto> accounts = accountsService.getAll();

        request.setAttribute("accounts", accounts);

        // отправляем request на страницу jdp
        //request.getRequestDispatcher("/WEB-INF/jsp/users_ver_1.jsp").forward(request, response);
        request.getRequestDispatcher("/WEB-INF/jsp/users_ver_2.jsp").forward(request, response);
    }
}
