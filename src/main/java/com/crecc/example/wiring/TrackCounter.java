package com.crecc.example.wiring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiyuanbupt on 2019/1/31.
 */

@Aspect
public class TrackCounter {
    private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();

    @Pointcut(
            "execution(* com.crecc.example.wiring.CompactDisc.playTrack(int))" +
                    "&& args(trackNumber)"
    )
    public void trackPlayed(int trackNumber){

    }

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber){
        int currentCount = getPlayCount(trackNumber);
    }

    public int getPlayCount(int trackNumber){
        return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber):0;
    }
}
