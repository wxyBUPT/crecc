package com.crecc.example.wiring.senior;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * Created by xiyuanbupt on 2019/3/1.
 */
@Configuration
@Profile("prod")
public class ProductionProfileConfig {
    @Bean
    public DataSource dataSource(){
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/myDS");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(
                DataSource.class
        );
        return (DataSource)jndiObjectFactoryBean.getObject();
    }
}
