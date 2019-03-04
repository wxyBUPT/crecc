package com.crecc.example.summary;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xiyuanbupt on 2019/1/30.
 * 基本的Spring功能, 实现了依赖注入和切片功能
 */
public class KnightMain {
    public static void main(String[] args) throws Exception{
        //ApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/spring/knights.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
