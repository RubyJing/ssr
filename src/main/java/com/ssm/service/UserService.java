package com.ssm.service;


import com.ssm.entity.User;
import com.ssm.entity.UserInfo;

public interface UserService extends BaseService{
    int insertOrUpdateUser(User user, UserInfo userInfo);
}
