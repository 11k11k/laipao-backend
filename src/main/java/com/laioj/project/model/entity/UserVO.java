package com.laioj.project.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

public class UserVO {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private Long id; // id 列应在表中存在

    @TableField("user_account")
    private String userAccount; // 账号列

    @TableField("user_password")
    private String userPassword; // 密码列

    @TableField("union_id")
    private String unionId; // 微信开放平台id列

    @TableField("mp_open_id")
    private String mpOpenId; // 公众号openId列

    @TableField("user_name")
    private String userName; // 用户昵称列

    @TableField("user_avatar")
    private String userAvatar; // 用户头像列

    @TableField("user_profile")
    private String userProfile; // 用户简介列

    @TableField("user_role")
    private String userRole; // 用户角色列

    @TableField("create_time")
    private Date createTime; // 创建时间列

    @TableField("update_time")
    private Date updateTime; // 更新时间列

    @TableField("is_delete")
    private Byte isDelete; // 是否删除列

    private String tags; // 标签列表列
}
