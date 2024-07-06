package com.laioj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laioj.project.model.entity.Tag;
import com.laioj.project.model.entity.User;

public interface TagService  extends IService<Tag> {

    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

}
