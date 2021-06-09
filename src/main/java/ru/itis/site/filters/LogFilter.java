package ru.itis.site.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

// получает все запросы и логирует информацию о них
// чтобы фильтр заработал, его нужно объявить в web.xml
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // отслеживаем какие запросы приходят
        System.out.println(LocalDateTime.now() + " " + request.getMethod() + " " + request.getRequestURI());
        // это обязательно, чтобы запрос прошёл дальше
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
