package com.ssm.service.impl;

import com.ssm.dao.UserInfoMapper;
import com.ssm.entity.UserInfo;
import com.ssm.service.BaseAbstractService;
import com.ssm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class UserInfoServiceImpl extends BaseAbstractService implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @PostConstruct
    public void init(){
        super.setBaseMapper(userInfoMapper);
    }

    //持久对象包装层
    private int addUserInfo(UserInfo userInfo){
        return userInfoMapper.addEntity(userInfo);
    }
    private int updateUserInfo(UserInfo userInfo){
        return userInfoMapper.updateByEntity(userInfo);
    }
    private UserInfo selectUserInfoBykey(String key){
        if (userInfoMapper.selectByPrimaryKey(key) instanceof UserInfo){
            return (UserInfo)userInfoMapper.selectByPrimaryKey(key);
        }else {
            return null;
        }
    }
    private int deleteUserInfoByKey(String key){
        return userInfoMapper.deleteByPrimaryKey(key);
    }

    //业务逻辑层
    protected void insertUserInfo(UserInfo userInfo){
        if(StringUtils.isEmpty(this.selectUserInfoBykey(userInfo.getUuid()))){
            this.addUserInfo(userInfo);
        }else {
            this.updateUserInfo(userInfo);
        }
    }

    //数据封装层
    private UserInfo toUserInfoDo(UserInfo userInfo){
        if(StringUtils.isEmpty(userInfo.getUuid())){
            userInfo.setUuid(UUID.randomUUID().toString());
        }
       return userInfo;
    }

    //流程控制层

    /**
     * 新增或更新用户信息
     * @param userInfo
     */
    public void insertOrUpdateUserInfo(UserInfo userInfo){
        UserInfo userInfo2 = this.toUserInfoDo(userInfo);
        this.insertUserInfo(userInfo2);
    }
}
