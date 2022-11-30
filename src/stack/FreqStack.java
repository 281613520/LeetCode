package stack;

import java.util.*;

public class FreqStack {
    Map<Integer, Integer> map = new HashMap<>();
    TreeMap<Integer, Stack<Integer>> freqQueueMap =new TreeMap<>();

    public FreqStack() {

    }

    public void push(int val) {
        map.put(val,map.getOrDefault(val,0)+1);
        int freq = map.get(val);

        if (freqQueueMap.containsKey(freq)){
            freqQueueMap.get(freq).push(val);
        }else {
            freqQueueMap.put(freq,new Stack<>());
            freqQueueMap.get(freq).push(val);
        }
    }

    public int pop() {
        int freq = freqQueueMap.lastKey();
        Stack<Integer> integers = freqQueueMap.get(freq);
        int res = integers.pop();
        int count = map.get(res);
        if (count == 1){
            map.remove(res);
        }else {
            map.put(res,count-1);
        }
        if (integers.isEmpty()){
            freqQueueMap.remove(freq);
        }


        return res;

    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}
