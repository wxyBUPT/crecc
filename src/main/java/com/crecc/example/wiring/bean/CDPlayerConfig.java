package com.crecc.example.wiring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiyuanbupt on 2019/1/30.
 */
@Configuration
public class CDPlayerConfig {
    @Bean
    public CompactDisc compactDisc(){
        return new SgtPeppers();
    }

    @Bean
    public CDPlayer cdPlayer(CompactDisc compactDisc){
        return new CDPlayer(compactDisc);
    }
}
