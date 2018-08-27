package com.conv.realm;

import com.conv.model.ActiveUser;
import com.conv.model.User;
import com.conv.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.session.HttpServletSession;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

    @Resource
    UserService userService;


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpServletRequest.getSession();

        if (autoLogin(httpServletRequest)) {
            return true;
        }

        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                String code = (String) httpSession.getAttribute("valiCode");
                String randomCode = (String) httpServletRequest.getParameter("captcha");
                if (code != null && randomCode != null && !code.equals(randomCode)) {
                    if (isAjax(httpServletRequest)) {
                        printCNJSON("{\"message\":\"验证码错误\"}", httpServletResponse);
                    } else {
                        httpServletRequest.setAttribute("shiroLoginFailure", "randomCodeError");
                        return true;
                    }
                } else {
                    return super.onAccessDenied(request, response);
                }

            } else {
                return true;
            }
        } else{
            if (isAjax(httpServletRequest)) {
                return true;
            }else {
                //返回配置的user/login.do，该方法会重定向到登陆页面地址，再次发送请求给本方法
                saveRequestAndRedirectToLogin(request, response);
            }
        }
        return false;
    }


    boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ActiveUser activeUser = (ActiveUser)subject.getPrincipal();

        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("activeUser",activeUser);

        if(isAjax(httpServletRequest)){
            printCNJSON("{\"message\":\"登陆成功\"}", httpServletResponse);
        }else {
            issueSuccessRedirect(request,response);
        }
        return false;
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String message = e.getClass().getSimpleName();

        if (!isAjax(httpServletRequest)) {
            setFailureAttribute(request, e);
            return true;
        }

        if ("IncorrectCredentialsException".equals(message)) {
           printCNJSON("{\"message\":\"密码错误\"}", httpServletResponse);
        } else if ("UnknownAccountException".equals(message)) {
            printCNJSON("{\"message\":\"账号不存在/没有在邮箱中激活账户\"}", httpServletResponse);
        } else if ("captchaCodeError".equals(message)) {
            printCNJSON("{\"message\":\"验证码错误\"}", httpServletResponse);
        } else {
           printCNJSON("{\"message\":\"未知错误\"}", httpServletResponse);
        }
        return false;

    }

    public void printCNJSON(String result, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(result);
            writer.flush();
            writer.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private boolean autoLogin(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();

        //如果 isAuthenticated 为 false 证明不是登录过的，同时 isRemember 为true 证明是没登陆直接通过记住我功能进来的
        if (!currentUser.isAuthenticated() && currentUser.isRemembered()) {

            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

            //获取session看看是不是空的
            Session session = currentUser.getSession();
            if (session.getAttribute("currentUser") == null) {

                User user = userService.validateUserExist(activeUser.getUserEmail());

                UsernamePasswordToken token = new UsernamePasswordToken(user.getUserEmail(), activeUser.getUserPassword(), currentUser.isRemembered());

                //把当前用户放入session
                currentUser.login(token);

                session.setAttribute("currentUser", user);
                //设置会话的过期时间--ms,默认是30分钟，设置负数表示永不过期
                session.setTimeout(-1000l);

                //这是httpSession、用户页面获取数据的。
                request.getSession().setAttribute("activeUser", activeUser);

                return true;
            }
        }

        return false;
    }

}

