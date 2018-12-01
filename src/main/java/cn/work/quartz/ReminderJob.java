package cn.work.quartz;

import cn.work.dao.BorrowMapper;
import cn.work.pojo.ReminderInfo;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: libraryOs
 * @description: 超期提醒任务
 * @author: Aaron Ke
 * @create: 2018-11-30 20:12
 **/
@Component
public class ReminderJob extends QuartzJobBean {

    @Resource
    BorrowMapper borrowMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始执行");
        List<ReminderInfo> todayList = borrowMapper.getAboutToOverDueRec(0);
        //还有三天过期的集合
        List<ReminderInfo> threeList = borrowMapper.getAboutToOverDueRec(3);
        if (todayList.size() > 0) {
            for (ReminderInfo reminderInfo : todayList) {
                sendMessage(reminderInfo, 1);
            }
        }
        if (threeList.size() > 0) {
            for (ReminderInfo reminderInfo : threeList) {
                sendMessage(reminderInfo, 2);
            }
        }
    }

    /**
     * @Description: 发送短信
     * @Param: type: 1:表示今天过期，2：表示3天后过期
     * @return:
     * @Author: Aaron Ke
     */
    private void sendMessage(ReminderInfo reminderInfo, int type) {
        switch (type) {
            case 1:
                System.out.println("亲爱的" + reminderInfo.getUsername() + ",您有" + reminderInfo.getBookNum() + "本图书将在今天过期，请注意归还");
                break;
            case 2:
                System.out.println("亲爱的" + reminderInfo.getUsername() + ",您有" + reminderInfo.getBookNum() + "本图书将在于3天后过期，请注意归还");
                break;
        }
    }
}
