package ru.itis.site.web.servlets;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.site.config.ApplicationConfig;
import ru.itis.site.dto.SearchAccountDto;
import ru.itis.site.services.AccountsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin/search")
// сервлет нельзя погрузить в контекст Spring
public class SearchServlet extends HttpServlet {


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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        PrintWriter writer = response.getWriter();
        // забираем query, который будет введён, т.е. query=bla
        String query = request.getParameter("query");

        List<SearchAccountDto> accounts = accountsService.search(query);
        // напечатаем просто в response
        writer.println(accounts);

    }
}
