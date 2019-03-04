package com.crecc.example.wiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiyuanbupt on 2019/1/30.
 */
@Component
public class CDPlayer implements MediaPlayer{
    private CompactDisc cd;
    @Autowired
    public CDPlayer(CompactDisc cd){
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
