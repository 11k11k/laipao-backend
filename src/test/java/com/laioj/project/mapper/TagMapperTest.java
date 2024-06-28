package com.laioj.project.mapper;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class TagMapperTest {
    private static TagMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new MybatisSqlSessionFactoryBuilder().build(TagMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/TagMapperTestConfiguration.xml"));
        final MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        builder.getConfiguration().addInterceptor(interceptor);
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(TagMapper.class, builder.openSession(true));

    }

    @Test
    public void testSelectByPrimaryKey() {
        mapper.selectByPrimaryKey(3L);
    }
}
