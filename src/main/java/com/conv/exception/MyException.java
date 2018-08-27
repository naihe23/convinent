package com.conv.exception;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 统一处理器类，所有的异常都将由该类处理
 */
public class MyException implements HandlerExceptionResolver {


    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {

        //输出异常
        ex.printStackTrace();

        String message = null;
        UserException userException = null;

        //如果ex是系统 自定义的异常，直接取出异常信息
        if (ex instanceof UserException) {
            userException = (UserException) ex;
        } else {
            //针对非UserException异常，对这类重新构造成一个UserException，异常信息为“未知错误”
            userException = new UserException("未知错误");
        }

        message = userException.getMessage();
        request.setAttribute("message", message);


        try {
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ModelAndView();
    }


}
