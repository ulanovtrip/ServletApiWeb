package ru.itis.site.jsp.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.site.config.ApplicationConfig;
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

@WebServlet("/users_old")
public class UsersServletOld extends HttpServlet {

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

        // теперь их нужно отобразить на странице браузера
        // для этого создадим и заполним accounts.html

        StringBuilder html = new StringBuilder();

        // здесь соберём "в лоб" html как строку
        // таблица и её заголовок
        html.append("<table>\n" +
                "    <tr>\n" +
                "        <th>ID</th>\n" +
                "        <th>FIRST NAME</th>\n" +
                "        <th>LAST NAME</th>\n" +
                "        <th>EMAIL</th>\n" +
                "    </tr>\n");

        // достаём из БД юзеров и вставляем в html
        for (AccountDto accountDto : accounts) {
            html.append("<tr>");
            html.append("<td>" + accountDto.getId() + "</td>");
            html.append("<td>" + accountDto.getFirstName() + "</td>");
            html.append("<td>" + accountDto.getLastName() + "</td>");
            html.append("<td>" + accountDto.getEmail() + "</td>");
            html.append("</tr>");
        }

        // закрылась таблица
        html.append("</table>");

        // записываем собранный html в response
        response.getWriter().println(html.toString());
    }
}
