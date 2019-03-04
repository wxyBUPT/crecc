package com.crecc.example.cd.bean;

import com.crecc.example.wiring.bean.CDPlayerConfig;
import com.crecc.example.wiring.bean.CompactDisc;
import com.crecc.example.wiring.bean.MediaPlayer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiyuanbupt on 2019/1/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private CompactDisc cd;
    @Autowired
    private MediaPlayer player;
    @Test
    public void cdShouldNotBeNull(){
        assertNotNull(cd);
    }
    @Test
    public void play(){
        player.play();;
        assertEquals(
                "playing  foo by The Beatles\n", systemOutRule.getLog()
        );
    }
}
