package com.laioj.project.controller;
import com.laioj.project.model.entity.Tag;
import com.laioj.project.service.impl.TagServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 标签(tag)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tag")
public class TagController {
/**
* 服务对象
*/
    @Autowired
    private TagServiceImpl tagServiceImpl;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public Tag selectOne(Integer id) {
    return tagServiceImpl.selectByPrimaryKey(Long.valueOf(id));
    }

}
