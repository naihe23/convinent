package com.conv.service;

import com.conv.model.User;

public interface UserService {

    void register(User user);

    User validateEmailService(String userEmail);

    User selectUserByUserId(int userId);

    void updateByPrimaryKeySelective(User user);

    void deleteByPrimaryKey(int userId);

    User validateUserExist(String userCode);
}
