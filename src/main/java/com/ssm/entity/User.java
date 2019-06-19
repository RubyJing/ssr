package com.ssm.entity;



import com.ssm.annotation.TestAnnotation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户
 */
@Data
@TestAnnotation(name = "User")
public class User extends AbstractUser {
  private String uuid;
  private String password;
  private Date createDate;
   private Date updateDate;
}
