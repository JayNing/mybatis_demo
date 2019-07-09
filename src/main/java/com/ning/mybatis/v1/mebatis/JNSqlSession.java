package com.ning.mybatis.v1.mebatis;

import java.lang.reflect.Proxy;

/**
 * @Author JAY
 * @Date 2019/7/9 19:31
 * @Description TODO
 **/
public class JNSqlSession {

    private JNConfiguration configuration;
    private JNExecutor executor;

    public JNSqlSession(JNConfiguration configuration,JNExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
        if (configuration.getCacheEnable().equals("true")){
            this.executor = new JNCachingExecutor(executor);
        }
    }

    public <T> T selectOne(String statement, Object args){
        //获取sql
        String sql = JNConfiguration.sqlMappings.getString(statement);
        if(null != sql && !"".equals(sql)){
            return executor.query(sql,args,configuration.getEnvironment());
        }
        return null;
    }

    public <T> T getMapper(Class clazz){
        return configuration.getMapper(clazz, this);
    }

}
