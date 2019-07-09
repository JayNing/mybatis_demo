package com.ning.mybatis.v1.mebatis;

import com.ning.mybatis.v1.dao.Blog;

import java.sql.*;

/**
 * @Author JAY
 * @Date 2019/7/9 20:05
 * @Description TODO
 **/
public class JNBaseExecutor extends JNExecutor{

    @Override
    public <T> T query(String sql, Object paramater, JNEnvironment environment) {
        Connection conn = null;
        Statement stmt = null;
        Blog blog = new Blog();

        try {
            // 注册 JDBC 驱动
            Class.forName(environment.getDriver());

            // 打开连接
            conn = DriverManager.getConnection(environment.getUrl(), environment.getUsername(), environment.getPassword());

            // 执行查询
            stmt = conn.createStatement();

            sql = sql.replace("?", paramater.toString());

            ResultSet rs = stmt.executeQuery(sql);

            // 获取结果集
            while (rs.next()) {
                Integer bid = rs.getInt("bid");
                String name = rs.getString("name");
                Integer authorId = rs.getInt("author_id");
                blog.setAuthorId(authorId);
                blog.setBid(bid);
                blog.setName(name);
            }
            System.out.println(blog);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return (T)blog;
    }
}
