package com.crecc.example.wiring.xml;

/**
 * Created by xiyuanbupt on 2019/1/30.
 * Component 表示这是一个类会作为组件类,
 * 没有必要显示配置SgtPeppersbean
 * 如下为自动命名
 */

public class SgtPeppers implements CompactDisc {
    private String title = "foo";
    private String artist = "The Beatles";
    public void play() {
        System.out.println("playing  " + title + " by " + artist);
    }
    public void playTrack(int trackNumber) {
    }
}
