package com.ssm.service;

import com.ssm.entity.AbstractUser;

public interface BaseService {
    /**
     * 新增实体
     * @param AbstractUser
     */
    int addEntity(AbstractUser abstractUser);
    /**
     * 通过主键查询
     * @param PrimaryKey
     * @return
     */
    AbstractUser selectByPrimaryKey(String PrimaryKey);

    /**
     * 更新通过主键
     * @param PrimaryKey
     */
    int updateByEntity(AbstractUser user);

    /**
     * 通过主键删除
     * @param PrimaryKey
     */
    int deleteByPrimaryKey(String PrimaryKey);
}
