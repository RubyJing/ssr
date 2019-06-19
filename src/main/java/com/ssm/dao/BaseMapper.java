package com.ssm.dao;


import com.ssm.entity.AbstractUser;

public interface BaseMapper {

    /**
     * 新增实体
     * @param entity
     */
    int addEntity(AbstractUser entity);
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
