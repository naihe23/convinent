package com.conv.service.impl;


import com.conv.model.User;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService{

    @Resource
    SimpleMailMessage simpleMailMessage;
    @Resource
    private TaskExecutor taskExecutor;
    @Resource
    private JavaMailSender javaMailSender;

    public void sendMail(User user ) throws  Exception{
        MimeMessage message  =  javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setFrom(simpleMailMessage.getFrom());
        helper.setSubject(simpleMailMessage.getSubject());
        helper.setTo(user.getUserEmail());
        StringBuffer str =  new StringBuffer("</br>请点击下面链接激活账号，24小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        str.append("<a href=\"http://localhost:8080/user/actiUser.do?userId=");
        str.append(user.getUserId());
        str.append("\">http://localhost:8080/user/actiUser.do?userId=");
        str.append(user.getUserId());
        str.append("\"</a>\"+\"<br/>如果以上链接无法点击，请把上面网页地址复制到浏览器地址栏中打开<br/>");
        helper.setText(str.toString(),true);
        sendMailTask(message);
    }

    public void sendForgetMail(User user ) throws  Exception{
        MimeMessage message  =  javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setFrom(simpleMailMessage.getFrom());
        helper.setSubject(simpleMailMessage.getSubject());
        helper.setTo(user.getUserEmail());
        StringBuffer str =  new StringBuffer("</br>请点击下面链接修改密码，24小时生效 ，链接只能使用一次，请尽快激活！</br>");
        str.append("<a href=\"http://localhost:8080/user/edit?userId=");
        str.append(user.getUserId());
        str.append("\">http://localhost:8080/user/edit?userId=");
        str.append(user.getUserId());
        str.append("\"</a>\"+\"<br/>如果以上链接无法点击，请把上面网页地址复制到浏览器地址栏中打开<br/>");
        helper.setText(str.toString(),true);
        sendMailTask(message);
    }

    private void sendMailTask( MimeMessage message) throws Exception{

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                javaMailSender.send(message);
            }
        });
    }
}
