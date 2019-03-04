package com.crecc.example.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

import org.springframework.context.annotation.Configuration;

/**
 * Created by xiyuanbupt on 2019/2/1.
 */

@Configuration
@ComponentScan(basePackages = {}, excludeFilters = {
        @ComponentScan.Filter
})
public class RootConfig {
}
