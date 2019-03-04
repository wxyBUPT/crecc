package com.crecc.example;

import com.crecc.example.summary.BraveKnight;
import com.crecc.example.summary.Quest;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by xiyuanbupt on 2019/1/30.
 */
public class BraveKnightTest {

    @Test
    public void knightShouldEmbarkOnQuest(){
        Quest mockQuest = mock(Quest.class);
        BraveKnight knight = new BraveKnight(mockQuest);
        knight.embarkOnQuest();
        verify(mockQuest, times(1)).embark();
    }
}
