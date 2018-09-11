package com.conv.controller;


import com.conv.util.ElasticSearchUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

@RequestMapping("/bookmark")
public class BookmarkController {

    @RequestMapping("/addWeb")
    @ResponseBody
    public String addWeb(String userId,@RequestParam(name = "webSiteAddr") String webAddress
            ,@RequestParam(name = "webSiteName") String webName) throws UnknownHostException{
        String result = ElasticSearchUtil.addWeb(webAddress,webName,userId);
        return result;
    }

    @RequestMapping("/queryWeb")
    @ResponseBody
    public HashMap<String,String> queryWeb(String userId) throws UnknownHostException {
        HashMap<String,String> map = ElasticSearchUtil.queryWeb(userId);
        return map;
    }

    @RequestMapping("/deleteWeb")
    @ResponseBody
    public String deleteWeb(String id) throws UnknownHostException{
        return ElasticSearchUtil.deleteWeb(id);
    }

    @RequestMapping("/queryByName")
    @ResponseBody
    public List<String> queryByName(String webSiteName, String userId)throws UnknownHostException{
        List<String> list  = new ArrayList<>();
        if(webSiteName==null||webSiteName.equals("")){
            list.add("nullName");
            return list;
        }
        list = ElasticSearchUtil.queryByNmae(userId,webSiteName);
        return list;
    }
}
