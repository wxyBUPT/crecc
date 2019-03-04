package com.crecc.example.summary;

/**
 * Created by xiyuanbupt on 2019/1/30.
 */
public class BraveKnight implements Knight {
    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    private Quest quest;

    public void embarkOnQuest() {
        quest.embark();
    }
}
