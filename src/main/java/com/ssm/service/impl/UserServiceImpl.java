package com.ssm.service.impl;

import com.ssm.dao.UserMapper;
import com.ssm.entity.AbstractUser;
import com.ssm.entity.User;
import com.ssm.entity.UserInfo;
import com.ssm.service.BaseAbstractService;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseAbstractService implements UserService {
    @Autowired
    private UserMapper userMapper;

    @PostConstruct
    public void init() {
        super.setBaseMapper(userMapper);
    }


    //持久对象包装层
    private int addUser(User user) {
        return userMapper.addEntity(user);
    }

    private void deleteUser(String key) {
        userMapper.deleteByPrimaryKey(key);
    }

    private User selectUser(String key) {
        AbstractUser abstractUser = userMapper.selectByPrimaryKey(key);
        if (abstractUser instanceof User) {
            return (User) abstractUser;
        } else {
            return null;
        }
    }

    private int updateUser(User user) {
        return userMapper.updateByEntity(user);
    }


    //业务逻辑层
    @Transactional(rollbackFor = Exception.class)
    protected int insertUser(User user) {
        User userDB = this.selectUser(user.getUuid());
        if (StringUtils.isEmpty(userDB)) {
            return this.addUser(user);
        } else {
            return this.updateUser(user);
        }
    }

    //数据封装层
    private User toUserDo(User user) {
        if (StringUtils.isEmpty(user.getUuid())) {
            user.setUuid(UUID.randomUUID().toString());
            user.setPassword("123456");
            user.setUsername(String.valueOf(Math.random() * 10000));
            user.setCreateDate(new Date());
        }
        user.setUpdateDate(new Date());
        return user;
    }

    //流程控制层

    /**
     * 新增或更新用户
     *
     * @param user
     */
    @Override
    public int insertOrUpdateUser(User user, UserInfo userInfo) {
        User user2 = this.toUserDo(user);
        return this.insertUser(user2);
    }

}
