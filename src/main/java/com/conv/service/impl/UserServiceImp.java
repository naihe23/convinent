package com.conv.service.impl;

import com.conv.dao.UserMapper;
import com.conv.model.User;
import com.conv.service.UserService;
import com.conv.util.EncryPassword;
import com.conv.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImp implements UserService {

    @Resource
    UserMapper userMapper;


    @Override
    public void register(User user) {
        User user1  = EncryPassword.encryptedPassword(user);
        user1.setTokenExptime(new Date());
        user1.setActiCode(UUIDUtils.getUUID());
        userMapper.insertSelective(user1);
    }

    @Override
    public User validateEmailService(String userEmail) {
        User user = userMapper.selectByUserEmail(userEmail);
        return user;
    }

    @Override
    public User selectUserByUserId(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateByPrimaryKeySelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteByPrimaryKey(int userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User validateUserExist(String userCode) {
        return userMapper.selectByUserName(userCode);
    }

}
