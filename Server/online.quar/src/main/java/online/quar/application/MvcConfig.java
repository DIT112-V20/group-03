package online.quar.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("pages/home");
        registry.addViewController("/").setViewName("pages/home");
        registry.addViewController("/hello").setViewName("pages/hello");
        registry.addViewController("/login").setViewName("pages/login");
        registry.addViewController("/carController").setViewName("pages/carController");
    }

}
