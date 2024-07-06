package com.laioj.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laioj.project.model.entity.User;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.laioj.project.model.entity.Tag;
import com.laioj.project.mapper.TagMapper;
import com.laioj.project.service.TagService;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Tag record) {
        return tagMapper.insert(record);
    }

    @Override
    public int insertSelective(Tag record) {
        return tagMapper.insertSelective(record);
    }

    @Override
    public Tag selectByPrimaryKey(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Tag record) {
        return tagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Tag record) {
        return tagMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean saveBatch(Collection<Tag> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Tag> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Tag> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Tag entity) {
        return false;
    }

    @Override
    public Tag getOne(Wrapper<Tag> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Tag> queryWrapper) {
        return Collections.emptyMap();
    }

    @Override
    public <V> V getObj(Wrapper<Tag> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Tag> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Tag> getEntityClass() {
        return null;
    }

}
