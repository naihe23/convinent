package com.conv.controller;

import com.conv.exception.UserException;
import com.conv.model.ActiveUser;
import com.conv.model.User;
import com.conv.service.UserService;
import com.conv.service.impl.EmailService;
import com.conv.util.CodeUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    EmailService emailService;

    @RequestMapping(value = "/register")
    public String registerUser() {
        return "register";
    }

    @RequestMapping("/login")
    public String loginDo() {
        return "user_login";
    }

    @RequestMapping("/forget")
    public String forget(){
        return "edit_password";
    }

    @RequestMapping(value = "/index.do")
    public String indexDo(){
        return "index";
    }
    /**
     * 用户注册,发送激活邮件
     *
     * @param user          注册的值
     * @param bindingResult 数据校验绑定
     * @param response
     * @throws Exception
     */
    @RequestMapping("/registerUser.do")
    public void register(@Validated User user, BindingResult bindingResult, HttpServletResponse response) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if (bindingResult.hasErrors()) {
            out.println("<script type='text/javascript'>");
            out.println("window.alert('请输入正确的数据!');");
            out.println("window.location.href='register'");
            out.println("</script>");
        }
        userService.register(user);
        emailService.sendMail(user);
        out.println("<script type='text/javascript'>");
        out.println("window.alert('注册成功!请24小时内到邮箱中激活!');");
        out.println("window.location.href='login.do'");
        out.println("</script>");
    }

    /**
     * 判断当前邮箱是否已注册
     *
     * @param userEmail 邮箱账号
     * @param writer
     * @throws Exception
     */
    @RequestMapping(value = "/validateEmail.do", method = RequestMethod.POST)
    public void validateEmail(String userEmail, PrintWriter writer) throws Exception {
        System.out.println("email:" + userEmail);
        User user = userService.validateEmailService(userEmail);
        if (user == null) {
            writer.write("nullEmail");
        } else if (user.getActiState() == 1) {
            writer.write("hasEmail");
        } else {
            writer.write("noEmail");

        }
    }

    /**
     * 激活用户
     *
     * @param userId 用户Id
     * @param model
     */
    @RequestMapping("/actiUser")
    public String actiUser(int userId, Model model) {
        User user = userService.selectUserByUserId(userId);
        String title = "";
        String content = "";
        String subject = "";


        if (user != null) {

            //得到当前时间和邮件时间对比,24小时内
            if (System.currentTimeMillis() - user.getTokenExptime().getTime() < 8640000) {
                user.setActiState(User.ACTIVATION_SUCCESSFUL);
                userService.updateByPrimaryKeySelective(user);
                title = "用户激活页面";
                subject = "用户激活";
                content = "恭喜您成功激活账户";
            } else {
                title = "激活失败页面";
                subject = "用户激活";
                content = "激活链接已超时，请重新注册";

                //删除记录已便用户再次注册
                userService.deleteByPrimaryKey(userId);

            }
        } else {
            title = "激活失败页面";
            subject = "用户激活";
            content = "用户已失效，请重新注册";
        }

        Map<String, Object> map = new HashedMap();
        map.put("title", title);
        map.put("content", content);
        map.put("subject", subject);
        model.addAttribute("map", map);
        return "activateSuccess";
    }

    /**
     * 从工具类中获取到验证码
     *
     * @param session
     * @param response
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    public void getCode(HttpSession session, HttpServletResponse response) {
        Map<String, Object> codeMap = CodeUtils.generateCodeAndPic();
        session.setAttribute("valiCode", codeMap.get("code").toString());
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("image/jpeg");

        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/userLogin.do")
    public String userLogin(HttpServletRequest request) throws Exception{
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exceptionClassName:"+exceptionClassName);
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
               throw new UserException("用户不存在或未激活");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new UserException("密码错误!");
            } else if ("randomCodeError".equals(exceptionClassName)) {
                throw new UserException("验证码错误!");
            }else
                throw new Exception();
        }
        return "user_login";
    }

    @RequestMapping(value = "/getUser.do",method = RequestMethod.POST)
    @ResponseBody
    public ActiveUser getUser(HttpSession session) {

        System.out.println("getUser");
        ActiveUser user = (ActiveUser) session.getAttribute("activeUser");
        System.out.println("active:"+user.getUserNickname());
        if (user == null) {
            return null;
        }
        return user;
    }

    @RequestMapping(value = "/forgetPassword.do",method = RequestMethod.POST)
    public void forgetPassword(){

    }
}