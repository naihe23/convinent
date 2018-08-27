package com.conv.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {
    private Integer userId;

    @NotNull(message="{user.not.null}")
    @Size(min=2,max=15,message="{user.userNickname.length.error}")
    private String userNickname;

    @NotNull(message="{user.not.null}")
    @Size(min=6,max=12,message="{user.userPassword.length.error}")
    private String userPassword;

    @NotNull(message="{user.not.null}")
    @Email(message="{user.userEmail.not.correct}")
    private String userEmail;

    private Integer actiState;

    private String actiCode;

    private String salt;

    private Date tokenExptime;

    public static final int ACTIVATION_SUCCESSFUL = 1;
    public static final int ACTIVATION_UNSUCCESSFUL = 0;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getActiState() {
        return actiState;
    }

    public void setActiState(Integer actiState) {
        this.actiState = actiState;
    }

    public String getActiCode() {
        return actiCode;
    }

    public void setActiCode(String actiCode) {
        this.actiCode = actiCode == null ? null : actiCode.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Date getTokenExptime() {
        return tokenExptime;
    }

    public void setTokenExptime(Date tokenExptime) {
        this.tokenExptime = tokenExptime;
    }
}