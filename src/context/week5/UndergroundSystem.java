package context.week5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 请你实现一个类 UndergroundSystem ，它支持以下 3 种方法：
 *
 * 1. checkIn(int id, string stationName, int t)
 *
 * 编号为 id 的乘客在 t 时刻进入地铁站 stationName 。
 * 一个乘客在同一时间只能在一个地铁站进入或者离开。
 * 2. checkOut(int id, string stationName, int t)
 *
 * 编号为 id 的乘客在 t 时刻离开地铁站 stationName 。
 * 3. getAverageTime(string startStation, string endStation)
 *
 * 返回从地铁站 startStation 到地铁站 endStation 的平均花费时间。
 * 平均时间计算的行程包括当前为止所有从 startStation 直接到达 endStation 的行程。
 * 调用 getAverageTime 时，询问的路线至少包含一趟行程。
 * 你可以假设所有对 checkIn 和 checkOut 的调用都是符合逻辑的。也就是说，如果一个顾客在 t1 时刻到达某个地铁站，那么他离开的时间 t2 一定满足 t2 > t1 。所有的事件都按时间顺序给出。
 */
public class UndergroundSystem {
    public class In{
        int id;
        String stationName;
        int t;

        public In(int id, String stationName, int t) {
            this.id = id;
            this.stationName = stationName;
            this.t = t;
        }
    }

    public class Out{
        int id;
        String stationName;
        int t;

        public Out(int id, String stationName, int t) {
            this.id = id;
            this.stationName = stationName;
            this.t = t;
        }
    }

    private Map<Integer,In> inRecord;

    private Map<Integer,Out> outRecord;

    private Map<String, Set<Integer>> inStation;


    public UndergroundSystem() {
        inRecord = new HashMap<>();
        outRecord = new HashMap<>();
        inStation = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        inRecord.put(id, new In(id,stationName,t));
        Set<Integer> tmp =  inStation.getOrDefault(stationName,new HashSet<>());
        tmp.add(id);
        inStation.put(stationName,tmp);
    }

    public void checkOut(int id, String stationName, int t) {
        outRecord.put(id,new Out(id,stationName,t));
    }

    public double getAverageTime(String startStation, String endStation) {
        double res = 0;

        int sum = 0;
        Set<Integer> ids =  inStation.get(startStation);
        int count = ids.size();
        for (int i : ids){
            int start  = inRecord.get(i).t;
            int end = outRecord.get(i).t;
            sum = sum + end - start;
        }

        res = (double)sum / count;

        return res;
    }

    public static void main(String[] args) {
        System.out.println((double) 11 /1);
    }
}
