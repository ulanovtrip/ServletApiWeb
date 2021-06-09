package ru.itis.site.listeners;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.site.config.ApplicationConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class SpringContextListeners implements ServletContextListener {

    private ApplicationContext springContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // при запуске контекста здесь получаем его объект
        // а контекст сервлетов имеет доступ ко всем сервлетам и наоборот
        // т.е. это объект, который шарится между всеми
        ServletContext servletContext = servletContextEvent.getServletContext();

        // доступ к контексту бинов, т.е. Spring
        ApplicationContext springContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // кладём в контекст сервлетов атрибут контекст Spring, теперь они связаны
        // теперь можно получить доступ в сервлетах
        servletContext.setAttribute("springContext", springContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // забираем бин и закрываем соединение, чтобы не было утечки памяти
        ((HikariDataSource) this.springContext.getBean(DataSource.class)).close();
    }
}
