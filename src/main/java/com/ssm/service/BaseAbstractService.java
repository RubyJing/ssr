package com.ssm.service;

import com.ssm.dao.BaseMapper;
import com.ssm.entity.AbstractUser;

public abstract class BaseAbstractService {
    private BaseMapper baseMapper;

    protected void setBaseMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 新增实体
     * @param entity
     */
    public int addEntity(AbstractUser abstractUser){
        return baseMapper.addEntity(abstractUser);
    }
    /**
     * 通过主键查询
     * @param PrimaryKey
     * @return
     */
    public AbstractUser selectByPrimaryKey(String PrimaryKey){
        return baseMapper.selectByPrimaryKey(PrimaryKey);
    }

    /**
     * 更新通过主键
     * @param user
     */
    public int updateByEntity(AbstractUser user){
        return baseMapper.updateByEntity(user);
    }

    /**
     * 通过主键删除
     * @param PrimaryKey
     */
    public int deleteByPrimaryKey(String PrimaryKey){
        return baseMapper.deleteByPrimaryKey(PrimaryKey);
    }


}
