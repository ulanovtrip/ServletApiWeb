package ru.itis.site.filters;

import lombok.SneakyThrows;
import ru.itis.site.forms.SignUpForm;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

public class FormConverterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @SneakyThrows // закрывает проверяемые исключения
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // если был выполнен POST
        if (request.getMethod().equals("POST")) {

            // список всех параметров, которые послали
            Map<String, String[]> body = request.getParameterMap();

            //
            Class<SignUpForm> signUpFormClass = SignUpForm.class;

            SignUpForm form = signUpFormClass.newInstance();

            // пройдёмся по ключам, получим все параметры запроса
            for (String paramName : body.keySet()) {
                Field field = signUpFormClass.getDeclaredField(paramName);
                // получаем все поля формы
                String currentField = field.getName();
                // во все поля вставить значения параметров
                // TODO: 09.06.2021 это лучше переписать на reflection
                switch (currentField) {
                    case ("firstName"):
                        form.setFirstName(request.getParameter("firstName"));
                        break;
                    case "lastName":
                        form.setLastName(request.getParameter("lastName"));
                        break;
                    case "email":
                        form.setEmail(request.getParameter("email"));
                        break;
                    case "password":
                        form.setPassword(request.getParameter("password"));
                        break;
                }
            }

            // в запрос положить атрибут, т.е. положить созданную форму как атрибут запроса
            request.setAttribute("form", form);
        }
        // это обязательно, чтобы запрос прошёл дальше
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
