package cn.work.quartz;

import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: libraryOs
 * @description: Quartz任务调度
 * @author: Aaron Ke
 * @create: 2018-11-30 20:15
 **/
@Component
public class QuartzScheduler {

    @Resource
    Scheduler scheduler;

    public void startReminder(String cron) throws SchedulerException {
        //实例化一个Job，名字为Reminder
        JobDetail detail = JobBuilder.newJob(ReminderJob.class).withIdentity("ReminderJob").build();
        //基于表达式构建触发器Trigger
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        //表达式触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("ReminderTrigger").withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(detail, cronTrigger);
    }

}
