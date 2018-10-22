package cn.work.spring.config;

import cn.work.interceptors.loginInterceptor;
import cn.work.interceptors.userLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor()).addPathPatterns("/index")
                .addPathPatterns("/index")
                .addPathPatterns("/Admin_changePwd")
                .addPathPatterns("/Admin_table")
                .addPathPatterns("/Book_table")
                .addPathPatterns("/Borrow")
                .addPathPatterns("/Borrow_table")
                .addPathPatterns("/Return")
                .addPathPatterns("/Ticket_table")
                .addPathPatterns("/User_table")
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin/login");

        registry.addInterceptor(new userLoginInterceptor()).addPathPatterns("/profile");
    }

    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }
}
