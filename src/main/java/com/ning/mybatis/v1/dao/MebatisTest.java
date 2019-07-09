package com.ning.mybatis.v1.dao;

import com.ning.mybatis.v1.mebatis.JNBaseExecutor;
import com.ning.mybatis.v1.mebatis.JNConfiguration;
import com.ning.mybatis.v1.mebatis.JNExecutor;
import com.ning.mybatis.v1.mebatis.JNSqlSession;

/**
 * @Author JAY
 * @Date 2019/7/9 19:31
 * @Description TODO
 **/
public class MebatisTest {

    public static void main(String[] args) {

        JNSqlSession sqlSession = new JNSqlSession(new JNConfiguration(), new JNBaseExecutor());
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.selectById(1);
        System.out.println("进行第二次查询，看看是否查询缓存");
        mapper.selectById(1);
    }

}
