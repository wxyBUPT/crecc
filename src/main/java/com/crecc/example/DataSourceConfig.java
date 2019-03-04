package com.crecc.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by xiyuanbupt on 2019/2/2.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("qa")
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
    @Primary
    @Profile("development")
    public DataSource embededDataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }
}
