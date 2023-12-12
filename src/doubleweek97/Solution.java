package doubleweek97;



import java.util.*;

public class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int cur : nums) {
            String curStr = String.valueOf(cur);
            for (int i = 0; i < curStr.length(); i++) {
                list.add(curStr.charAt(i) - '0');
            }
        }

        int[] res = new int[list.size()];


        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> set = new HashSet<>();

        for (int cur : banned) {
            set.add(cur);
        }

        int sum = 0;
        int count = 0;


        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                sum += i;
                if (sum <= maxSum) {
                    count++;
                } else {
                    break;
                }
            }
        }

        return count;
    }

    public int maximizeWin(int[] prizePositions, int k) {
       int left = 0;
       int right = 0;
       int n = prizePositions.length;

       int[] pre = new int[n+1];

       int ans = 0;

       for (;right < n ; right++){
           while (prizePositions[right] - prizePositions[left] > k) left++;
           ans  = Math.max(ans,pre[left] + right - left +1);

           pre[right + 1] =Math.max(pre[right] , right - left +1);
       }

       return ans;
    }
}
