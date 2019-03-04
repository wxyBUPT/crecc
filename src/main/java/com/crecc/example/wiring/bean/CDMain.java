package com.crecc.example.wiring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by xiyuanbupt on 2019/1/30.
 */
public class CDMain {
    public static void main(String[] args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CDPlayerConfig.class);
        CDPlayer cdPlayer = (CDPlayer) applicationContext.getBean("cdPlayer");
        cdPlayer.play();

    }
}
