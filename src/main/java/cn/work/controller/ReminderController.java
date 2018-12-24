package cn.work.controller;

import cn.work.quartz.QuartzScheduler;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: libraryOs
 * @description: 超期提醒的控制器
 * @author: Aaron Ke
 * @create: 2018-12-01 11:00
 **/
@RestController
@RequestMapping("/reminder")
public class ReminderController {
    @Autowired
    QuartzScheduler quartzScheduler;

    @Autowired
    Scheduler scheduler;


    //得到当前超期提醒是否开启
    @RequestMapping("/getCurrentState")
    public Map<String, Object> getCurrentState() {
        Map<String, Object> result = new HashMap<>();
        JobDetail jobDetail = null;
        JobKey jobKey = new JobKey("ReminderJob");
        TriggerKey triggerKey = new TriggerKey("ReminderTrigger");
        try {
            jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null) {
                result.put("state", "0");
            } else {
                Trigger trigger = scheduler.getTrigger(triggerKey);
                Date date = trigger.getNextFireTime();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String sDate = sdf.format(date);
                result.put("time", sDate);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description: 控制Reminder
     * @Param: date:如果为空，说明要关闭超期提醒功能。
     * @return: 操作结果
     * @Author: Aaron Ke
     */
    @RequestMapping("/startReminder")
    public Map<String, Object> startReminder(String date) {
        Map<String, Object> result = new HashMap<>();
        JobKey jobKey = new JobKey("ReminderJob");
        JobDetail jobDetail;
        try {
            if (!StringUtils.isEmpty(date)) {
                String cron = getCronExpression(date);

                jobDetail = scheduler.getJobDetail(jobKey);
                if (jobDetail != null) {
                    scheduler.deleteJob(jobKey);
                }
                quartzScheduler.startReminder(cron);
                result.put("result", "success");
                result.put("msg", "开启成功");
            } else {
                jobDetail = scheduler.getJobDetail(jobKey);
                if (jobDetail != null) {
                    scheduler.deleteJob(jobKey);
                }
                result.put("result", "success");
                result.put("msg", "关闭成功");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            result.put("result", "error");
            result.put("msg", "服务器正忙，稍候重试！");
            return result;
        }
        return result;
    }

    private String getCronExpression(String date) {
        String[] times = date.split(":");
        return "0 " + times[1] + " " + times[0] + " ? * *";
    }
}
