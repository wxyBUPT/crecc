package com.crecc.example.dbcp;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by xiyuanbupt on 2019/2/11.
 */
public class SQLServerDBCPUtil {
    private static Properties properties = new Properties();
    private static DataSource dataSource;
    //加载DBCP配置文件
    static {
        try{
            System.out.println(
                    "Working Directory = " +
                            System.getProperty("user.dir")
            );
            FileInputStream config =
                    new FileInputStream("src/main/resources/sqlserverdbcp.properties");
            //InputStream config = KCYDBCPUtil.class.getClassLoader().getResourceAsStream("resources/dbcp.properties");
            InputStreamReader inputStreamReader = new InputStreamReader(config, "UTF-8");
            properties.load(inputStreamReader);
            /**
            BufferedReader bfr = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bfr.readLine()) != null){
                System.out.println(line);
            };
            config =
                    new FileInputStream("src/main/resources/sqlserverdbcp.properties");
            properties.load(config);
             **/
            System.out.println(properties.getProperty("url"));
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //从连接池中获取一个连接
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args)throws Exception{
        Connection conn = SQLServerDBCPUtil.getConnection();
        String sql = "select *  from 定额库xml";
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(resultSet.getString("定额编号"));
        }
    }
}
