package com.crecc.example.jdbbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by xiyuanbupt on 2019/2/2.
 */
@Configuration
@ComponentScan("com")
public class JTemplateConf {

    @Bean
    public DataSource dataSource(){
        BasicDataSource basicDataSource;
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/dbcp.properties"));
            basicDataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
            basicDataSource = null;
        }
        return basicDataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
