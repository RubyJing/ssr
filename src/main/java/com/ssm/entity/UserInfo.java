package com.ssm.entity;

import com.ssm.annotation.TestAnnotation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息
 */
@Data
public class UserInfo extends AbstractUser {
    private String uuid;


    private String userRealName;


    private String sex;


    private Integer phoneNumber;


    private String email;


    private String userUUID;
}
