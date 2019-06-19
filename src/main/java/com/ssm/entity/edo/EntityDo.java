package com.ssm.entity.edo;

import com.ssm.entity.User;
import com.ssm.entity.UserInfo;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntityDo {
    private User user;
    private UserInfo userInfo;
}
