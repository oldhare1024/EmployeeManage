package com.iweb.employee.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

//DataSource:连接池称连接数据库的基本信息
public class DruidUtil {

    private static DataSource dataSource;
    static {
        //把db.properties文件内容读取到输入字节流中
        InputStream inputStream =
                DruidUtil.class.getClassLoader().
                getResourceAsStream("resources/db.properties");

        Properties p = new Properties();
        try {
            //把流数据封装到p中
            p.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据源
    public static DataSource getDataSource(){
        return dataSource;
    }
}
