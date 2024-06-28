package com.laioj.project.model.entity;

import java.util.Date;

/**
 * 标签
 */
public class Tag {
    /**
    * id
    */
    private Long id;

    /**
    * 标签名称
    */
    private String tagName;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 父标签 id
    */
    private Long parentId;

    /**
    * 0-不是， 1-标签
    */
    private Byte idParent;

    /**
    * 创建时间
    */
    private Date createTime;

    private Date updateTime;

    /**
    * 是否删除
    */
    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Byte getIdParent() {
        return idParent;
    }

    public void setIdParent(Byte idParent) {
        this.idParent = idParent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}