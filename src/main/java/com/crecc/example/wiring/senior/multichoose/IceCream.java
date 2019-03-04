package com.crecc.example.wiring.senior.multichoose;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by xiyuanbupt on 2019/3/1.
 */

@Component
@Qualifier("cold")
public class IceCream implements Dessert{
}
