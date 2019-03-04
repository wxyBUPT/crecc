package com.crecc.example.wiring.senior;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

/**
 * Created by xiyuanbupt on 2019/3/1.
 */

@Configuration
@Profile("dev")
public class DevelopmentProfileConfig {
    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .addScript("classpath:schema.sql")
                .addScript("test-data.sql").build();
    }
}
