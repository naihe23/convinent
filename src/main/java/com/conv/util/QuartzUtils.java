package com.conv.util;

import com.conv.model.Memo;
import com.conv.service.MemoService;
import com.conv.service.impl.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class QuartzUtils implements Job {

    @Autowired
    EmailService emailService;
    @Autowired
    MemoService memoService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        Map<String,Object> map = jobExecutionContext.getJobDetail().getJobDataMap().getWrappedMap();
        Memo memo = (Memo)map.get("memo");
        emailService.sendMemo(memo);
        memo.setState(1);
        memoService.updateByPrimaryKey(memo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
