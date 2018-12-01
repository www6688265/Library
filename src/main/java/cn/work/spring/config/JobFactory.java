package cn.work.spring.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @program: libraryOs
 * @description: Quartz任务工厂
 * @author: Aaron Ke
 * @create: 2018-11-30 22:20
 **/

@Component
public class JobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 实例化对象
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入（Spring管理该Bean）
        capableBeanFactory.autowireBean(jobInstance);
        //返回对象
        return jobInstance;
    }
}
