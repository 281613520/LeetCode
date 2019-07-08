package BackTracing;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> grayCode(int n) {
        if(n == 0){
            List<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }

        List<Integer> res = grayCode(n-1);

        int originSize = res.size();
        int addN = 1 << (n-1);

        for(int i = originSize-1;i>=0;i--)
            res.add(addN + res.get(i));
        return res;
    }
}
