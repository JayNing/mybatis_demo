package com.ning.mybatis.v1.mebatis;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author JAY
 * @Date 2019/7/9 19:33
 * @Description TODO
 **/
public class JNConfiguration {


    public static final ResourceBundle sourceMappings;
    //statementId和sql映射
    public static final ResourceBundle sqlMappings;

    private String cacheEnable;

    //数据库配置
    private JNEnvironment environment;

    static{
        sourceMappings = ResourceBundle.getBundle("application");
        sqlMappings = ResourceBundle.getBundle("sql");
    }

    public JNEnvironment getEnvironment() {
        if (environment == null){
            String driverClass = sourceMappings.getString("driverClass");
            String url = sourceMappings.getString("url");
            String username = sourceMappings.getString("username");
            String password = sourceMappings.getString("password");
            environment = new JNEnvironment(driverClass,url,username,password);
        }
        return environment;
    }

    public String getCacheEnable() {
        cacheEnable = sourceMappings.getString("cacheEnable");
        return cacheEnable;
    }

    public void setCacheEnable(String cacheEnable) {
        this.cacheEnable = cacheEnable;
    }

    public void setEnvironment(JNEnvironment environment) {
        this.environment = environment;
    }

    public <T> T getMapper(Class clazz, JNSqlSession jnSqlSession) {
        return  (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new JNMapperProxy(jnSqlSession));
    }
}
