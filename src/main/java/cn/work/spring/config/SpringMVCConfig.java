package cn.work.spring.config;

import cn.work.interceptors.LoginInterceptor;
import cn.work.interceptors.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import static cn.work.spring.config.LibraryConfig.projectCachePath;
import static cn.work.spring.config.LibraryConfig.projectPath;
import static cn.work.spring.config.LibraryConfig.projectUploadPath;

@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/index")
                .addPathPatterns("/index")
                .addPathPatterns("/Admin_changePwd")
                .addPathPatterns("/Admin_table")
                .addPathPatterns("/Book_table")
                .addPathPatterns("/Borrow")
                .addPathPatterns("/Borrow_table")
                .addPathPatterns("/Return")
                .addPathPatterns("/Ticket_table")
                .addPathPatterns("/User_table")
                .excludePathPatterns("/admin/login");
        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/profile");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("frontIndex");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(projectUploadPath + "/**").addResourceLocations("file:" + projectPath + projectUploadPath + "/");
        registry.addResourceHandler(projectCachePath + "/**").addResourceLocations("file:" + projectPath + projectCachePath + "/");
    }
}
