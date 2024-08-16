package com.laioj.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * 用户队伍关系
 */
@Data
@TableName(value = "user_team")
public class UserTeam {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 队伍id
     */
    @TableField(value = "teamId")
    private Long teamId;

    /**
     * 加入时间
     */
    @TableField(value = "joinTime")
    private Date joinTime;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "isDelete")
    @TableLogic
    private Byte isDelete;
}