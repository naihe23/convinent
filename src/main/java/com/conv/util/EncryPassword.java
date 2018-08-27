package com.conv.util;

import com.conv.model.User;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

public class EncryPassword {

    public static User encryptedPassword(User user){
        String salt = UUID.randomUUID().toString();
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(),salt,2);
        user.setUserPassword(md5Hash.toString());
        user.setSalt(salt);
        return user;
    }
}
