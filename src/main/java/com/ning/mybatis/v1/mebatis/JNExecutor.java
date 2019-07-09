package com.ning.mybatis.v1.mebatis;

import com.ning.mybatis.v1.dao.Blog;

import java.sql.*;

/**
 * @Author JAY
 * @Date 2019/7/9 20:05
 * @Description TODO
 **/
public abstract class JNExecutor {

    public abstract  <T> T query(String sql, Object paramater, JNEnvironment environment) ;
}
