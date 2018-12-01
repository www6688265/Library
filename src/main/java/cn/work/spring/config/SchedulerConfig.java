package cn.work.spring.config;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * @program: libraryOs
 * @description: Quartz配置
 * @author: Aaron Ke
 * @create: 2018-11-30 20:07
 **/
@Configuration
public class SchedulerConfig {
    @Autowired
    JobFactory jobFactory;

    @Bean(name = "Scheduler")
    public SchedulerFactoryBean SchedulerFactoryBean() {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(jobFactory);
        return factoryBean;
    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

}
