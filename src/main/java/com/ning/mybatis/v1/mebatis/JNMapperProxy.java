package com.ning.mybatis.v1.mebatis;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author JAY
 * @Date 2019/7/9 19:42
 * @Description TODO
 **/
public class JNMapperProxy<T>  implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private JNSqlSession sqlSession;

    public JNMapperProxy(JNSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterface + "." + methodName;
        return sqlSession.selectOne(statementId, args[0]);
    }
}
