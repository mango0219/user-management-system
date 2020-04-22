package cn.codewei.manageUsers.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    private static Connection co;
    private static DataSource ds;
    static {
        try {
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
         return ds.getConnection();
    }

    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 释放资源
     * @param sta
     * @param co
     */
    public static void close(Statement sta,Connection co){
        close(null,sta,co);
    }


    /**
     * 释放资源重载
     * @param res
     * @param sta
     * @param co
     */
    public static void close(ResultSet res ,Statement sta,Connection co){
        if (res != null){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (sta != null){
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (co != null){
            try {
                co.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
