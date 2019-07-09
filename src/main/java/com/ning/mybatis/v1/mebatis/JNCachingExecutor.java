package com.ning.mybatis.v1.mebatis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author JAY
 * @Date 2019/7/9 20:21
 * @Description TODO
 **/
public class JNCachingExecutor extends JNExecutor{

    private JNExecutor executor;

    private static final Map<String, Object> cacheMap = new HashMap<>();

    public JNCachingExecutor(JNExecutor executor) {
        this.executor = executor;
    }

    @Override
    public <T> T query(String sql, Object paramater, JNEnvironment environment) {
        String cacheKey = sql + paramater.toString();
        System.out.println("本次cacheKey = " + cacheKey);
        if (cacheMap.get(cacheKey) != null){
            System.out.println("走到了一级缓存，不需要查询数据库");
            System.out.println(cacheMap.get(cacheKey));
            return (T) cacheMap.get(cacheKey);
        } else {
            T result = executor.query(sql, paramater, environment);
            cacheMap.put(cacheKey, result);
        }

        return (T) cacheMap.get(cacheKey);
    }
}
