package com.conv.util;

import com.conv.model.Memo;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class QuartzManager {

    private static final String JOB_GROUP_NAME = "my_job";
    private static final String TRIGGER_GROUP_NAME = "my_trigger";

    public static void addJob(Scheduler scheduler, @SuppressWarnings("rawtypes") Class jonClass, Memo memo){
        HashMap<String,Object> map = new HashMap<>();
        map.put("memo",memo);
        try {
            System.out.println("addMemoId:"+memo.getMemoId());
        JobDetail jobDetail = JobBuilder.newJob(jonClass).withIdentity(String.valueOf(memo.getMemoId()),JOB_GROUP_NAME)
                .usingJobData(new JobDataMap(map)).build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(String.valueOf(memo.getMemoId()),TRIGGER_GROUP_NAME)
                .withSchedule(CronScheduleBuilder.cronSchedule(getCron(memo.getSendTime()))).build();
            scheduler.scheduleJob(jobDetail,cronTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    public static String getCron(java.util.Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    public static void modifyJobTime(Scheduler sched,Memo memo) {
        try {
            System.out.println("sendTime1:"+memo.getSendTime());
            System.out.println("modifyMemoId:"+memo.getMemoId());
            CronTrigger trigger = (CronTrigger) sched.getTrigger(new TriggerKey(String.valueOf(memo.getMemoId()), TRIGGER_GROUP_NAME));
            if (trigger == null) {
                System.out.println("nullTrigger");
                return;
            }
            String oldTime = trigger.getCronExpression();
            System.out.println("oldTime:"+oldTime);
            System.out.println("sendTime2:"+memo.getSendTime());
            if (!oldTime.equalsIgnoreCase(getCron(memo.getSendTime()))) {
                System.out.println("tttest");
                JobDetail jobDetail = sched.getJobDetail(new JobKey(String.valueOf(memo.getMemoId()), JOB_GROUP_NAME));
                Class objJobClass = jobDetail.getJobClass();
                removeJob(sched, String.valueOf(memo.getMemoId()),String.valueOf(memo.getMemoId()));
                addJob(sched, objJobClass,memo);
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeJob(Scheduler sched, String jobName, String triggerName) {
        try {
            sched.pauseTrigger(new TriggerKey(triggerName, TRIGGER_GROUP_NAME));// 停止触发器
            sched.unscheduleJob(new TriggerKey(triggerName, TRIGGER_GROUP_NAME));// 移除触发器
            sched.deleteJob(new JobKey(jobName, JOB_GROUP_NAME));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
