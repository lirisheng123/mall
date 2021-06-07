package com.springboot.cloud.util;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;



/**
 * @Author: lirisheng
 * @Date: 2021/5/5 18:23
 * @Version 1.0
 */
public class MyDataModel {

    public static JDBCDataModel myDataModel() {
        MysqlDataSource dataSource = new MysqlDataSource();
        JDBCDataModel dataModel = null;
        try {
            dataSource.setServerName("localhost");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            dataSource.setDatabaseName("mallProduct");


            ConnectionPoolDataSource connectionPool=new ConnectionPoolDataSource(dataSource);
            // use JNDI
            dataModel = new MySQLJDBCDataModel(connectionPool,"mall_recommend", "user_id", "good_id","preference","create_time");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dataModel;
    }

}
