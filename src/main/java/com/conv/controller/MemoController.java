package com.conv.controller;


import com.conv.model.Memo;
import com.conv.model.User;
import com.conv.service.MemoService;
import com.conv.service.UserService;
import com.conv.util.NameUtil;
import com.conv.util.QuartzManager;
import com.conv.util.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/memo")
public class MemoController {

    @Resource
    MemoService memoService;
    @Autowired
    Scheduler scheduler;
    @Autowired
    UserService userService;
    /**
     * 日期转换器，能够将页面传递进来的string ：2017-12-8 10:50:51 进行解析
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

    }


    @RequestMapping("/addMemo")
    public String addMemo(Memo memo){
        memo.setMemoContent(NameUtil.toCN(memo.getMemoContent()));
        memoService.insert(memo);
        QuartzManager.addJob(scheduler, QuartzUtils.class,memo);
        return "index";
    }

    @RequestMapping("/queryAllMemo")
    @ResponseBody
    public HashMap<String,Object> queryAllMemo(String userId){
        List<Memo> list = memoService.selectByUserId(Integer.parseInt(userId));
        User user  = userService.selectUserByUserId(Integer.parseInt(userId));
        HashMap<String,Object> map = new HashMap<>();
        map.put("nickName",user.getUserNickname());
        map.put("list",list);
        map.put("email",user.getUserEmail());
        return map;
    }

    @RequestMapping("/editMemo")
    @ResponseBody
    public String editMemo(Memo memo){
        if(memo == null){
            return "null";
        }
       int n = memoService.updateByPrimaryKeySelective(memo);
        System.out.println("newTime:"+memo.getSendTime());
        QuartzManager.modifyJobTime(scheduler,memo);
        if(n>0){
            return "success";
        }else
            return "fail";
    }

    @RequestMapping("/deleteMemo")
    public String deleteMemo(String memoId){
        memoService.deleteByPrimaryKey(Integer.parseInt(memoId));
        QuartzManager.removeJob(scheduler,memoId,memoId);
        return "memo";
    }
}
