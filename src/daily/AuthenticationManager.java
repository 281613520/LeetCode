package daily;

import context.week5.UndergroundSystem;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
    Map<String, Integer> tokenMap = new HashMap<>();

    int timeToLive;
    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        tokenMap.put(tokenId,currentTime+ timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        if (tokenMap.containsKey(tokenId)){
            if (tokenMap.get(tokenId) < currentTime){
                tokenMap.put(tokenId,currentTime+timeToLive);
            }
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int res = 0;
        for (int time : tokenMap.values()) {
            if (time > currentTime) {
                res++;
            }
        }
        return res;
    }
}
