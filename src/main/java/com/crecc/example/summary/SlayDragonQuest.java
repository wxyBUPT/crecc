package com.crecc.example.summary;


import java.io.PrintStream;

/**
 * Created by xiyuanbupt on 2019/1/30.
 */
public class SlayDragonQuest implements Quest {
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }
}
