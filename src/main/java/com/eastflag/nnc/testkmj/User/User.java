package com.eastflag.nnc.testkmj.User;

import com.eastflag.nnc.testkmj.RoleId;

import java.util.UUID;

public class User {
    private final UUID user_id;
    private final String name;
    private final String tel_num;
    private final RoleId roleId;

    public User(UUID userId, String name, String telNum, RoleId roleId) {
        user_id = userId;
        this.name = name;
        tel_num = telNum;
        this.roleId = roleId;
    }

    public UUID getUserId() {
        return user_id;
    }
    public String getName() {
        return name;
    }
    public String getTelNum() {
        return tel_num;
    }
    public RoleId getRoleId() {
        return roleId;
    }
}
