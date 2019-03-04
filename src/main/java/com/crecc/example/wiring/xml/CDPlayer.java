package com.crecc.example.wiring.xml;

/**
 * Created by xiyuanbupt on 2019/1/30.
 */
public class CDPlayer implements MediaPlayer {
    private CompactDisc cd;
    public CDPlayer(CompactDisc cd){
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
