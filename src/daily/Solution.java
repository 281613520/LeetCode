package daily;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] dislike : dislikes) {
            if (graph.containsKey(dislike[0])) {
                graph.get(dislike[0]).add(dislike[1]);
            } else {
                graph.put(dislike[0], new HashSet<Integer>() {{
                    add(dislike[1]);
                }});
            }
            if (graph.containsKey(dislike[1])) {
                graph.get(dislike[1]).add(dislike[0]);
            } else {
                graph.put(dislike[1], new HashSet<Integer>() {{
                    add(dislike[0]);
                }});
            }
        }

        // 默认丢在set1 中
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            if (!set1.contains(i) && !set2.contains(i)) {
                if (!dfs(i, set1, set2, graph, 1)) {
                    return false;
                }
            }
        }


        return true;
    }

    private boolean dfs(int num, Set<Integer> set1, Set<Integer> set2, Map<Integer, Set<Integer>> graph, int status) {
        Set<Integer> tmp = graph.getOrDefault(num, new HashSet<>());

        if (status == 1) {
            set1.add(num);
        } else if (status == 2) {
            set2.add(num);
        }

        for (int value : tmp) {
            if ((set1.contains(value) && set1.contains(num)) || (set2.contains(value) && set2.contains(num))) {
                return false;
            }

            if (!set1.contains(value) && !set2.contains(value)) {
                if (!dfs(value, set1, set2, graph, 3 - status)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        backtrace(s, 0, ans, "");
        return ans;
    }

    private void backtrace(String s, int location, List<String> ans, String tmp) {
        StringBuilder tmpBuilder = new StringBuilder(tmp);
        while (location < s.length() && Character.isDigit(s.charAt(location))) {
            tmpBuilder.append(s.charAt(location));
            location++;
        }
        tmp = tmpBuilder.toString();

        if (location == s.length()) {
            ans.add(tmp);
            return;
        }

        tmp += Character.toLowerCase(s.charAt(location));
        backtrace(s, location + 1, ans, tmp);
        tmp = tmp.substring(0, tmp.length() - 1) + Character.toUpperCase(s.charAt(location));
        backtrace(s, location + 1, ans, tmp);
    }


    public int reachNumber(int target) {
        target = Math.abs(target);
        int tmp = target;
        int k = 1;

        while (tmp > 0) {
            tmp -= k;
            k++;
        }

        if (tmp == 0) {
            return k - 1;
        } else {
            int diff = target - tmp;
            return k - 2 + diff * 2;
        }
    }


    public List<String> ambiguousCoordinates(String s) {
        List<String> ans = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }

        String after = sb.toString();


        for (int i = 1; i <= after.length(); i++) {
            String first = after.substring(0, i);
            String second = after.substring(i);
            if (second.equals("")) {
                continue;
            }
            if (Integer.parseInt(first) == 0 && first.length() > 1 || Integer.parseInt(second) == 0 && second.length() > 1) {
                continue;
            } else {
                List<String> a = new ArrayList<>();
                List<String> b = new ArrayList<>();

                decode(first, a);
                decode(second, b);

                for (String item : a) {
                    for (String value : b) {
                        ans.add("(" + item + ", " + value + ")");
                    }
                }

            }
        }

        return ans;
    }
    //(0,1.23)

    private void decode(String str, List<String> list) {
        for (int i = 1; i <= str.length(); i++) {
            String first = str.substring(0, i);
            String second = str.substring(i);
            if (second.equals("")) {
                if (!(first.startsWith("0") && first.length() > 1)) {
                    list.add(first);
                }
            } else {
                if (first.startsWith("0") && first.length() > 1 || second.endsWith("0")) {
                } else {
                    list.add(first + "." + second);
                }
            }
        }
    }


    public int numTilings(int n) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][4];

        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][3];
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % mod + dp[i - 1][2]) % mod + dp[i - 1][3]) % mod;
        }

        return dp[n][3];
    }


    public boolean splitArraySameAverage(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        // 问题可以转化为 求前半数组是否有和0 或者不为0就爆保存下来 看后半数组有没有能和他+起来为0
        //如果 不折半 则需要 枚举所有的状态， 如果为n 则为 2^n次方 折半了就是 2 * 2^(n/2) 有效缩减
        int n = nums.length, m = n / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * n - sum;
        }

        Set<Integer> left = new HashSet<Integer>();
        // 这里则是使用二进制来进行枚举，总共有2^(1<<m)种状态
        // 每次0 检查i的每一位 为true 则选这一位 ，加上 这样就全部枚举了
        for (int i = 1; i < (1 << m); i++) {
            int tot = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    tot += nums[j];
                }
            }
            if (tot == 0) {
                return true;
            }
            left.add(tot);
        }
        int rsum = 0;
        for (int i = m; i < n; i++) {
            rsum += nums[i];
        }
        for (int i = 1; i < (1 << (n - m)); i++) {
            int tot = 0;
            for (int j = m; j < n; j++) {
                if ((i & (1 << (j - m))) != 0) {
                    tot += nums[j];
                }
            }
            if (tot == 0 || (rsum != tot && left.contains(-tot))) {
                return true;
            }
        }
        return false;
    }


    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);

        int ans = 0;
        for (int[] boxType : boxTypes) {
            int numberOfBoxes = boxType[0];
            int numberOfUnitsPerBox = boxType[1];

            if (numberOfBoxes < truckSize) {
                ans += numberOfBoxes * numberOfUnitsPerBox;
                truckSize -= numberOfBoxes;
            } else {
                ans += truckSize * numberOfUnitsPerBox;
                break;
            }
        }

        return ans;
    }

    public boolean isIdealPermutation(int[] nums) {
        int countAll = 0;
        int countPart = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            if (num > i) {
                countAll += num - i;
            }
            if (num > nums[i + 1]) {
                if (num == i) {
                    countAll++;
                }
                countPart++;
            }
        }

        return countPart == countAll;

    }

    public double largestSumOfAverages(int[] nums, int k) {
        double[] preSum = new double[nums.length];
        preSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }

        double[][] dp = new double[nums.length + 10][k + 10];

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                if (j == 1) {
                    dp[i][j] = preSum[i] / i;
                } else {
                    for (int l = 2; l <= i; l++) {
                        dp[i][j] = Math.max(dp[i][j], dp[l - 1][j - 1] + (preSum[i] - preSum[l - 1]) / (i - l + 1));
                    }
                }
            }
        }

        return dp[nums.length][k];

    }


    public int minOperations(String s) {
        int res_10 = 0;
        int res_01 = 0;
        //10
        //01
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) != '1') {
                    res_10++;
                } else if (s.charAt(i) != '0') {
                    res_01++;
                }
            } else {
                if (s.charAt(i) != '0') {
                    res_10++;
                } else if (s.charAt(i) != '1') {
                    res_01++;
                }
            }
        }

        return Math.min(res_01, res_10);
    }

    int res;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        res = Integer.MAX_VALUE;
        for (int cur : baseCosts) {
            closestCostBacktrace(0, toppingCosts, cur, target);
        }

        return res;
    }

    private void closestCostBacktrace(int location, int[] toppingCosts, int cur, int target) {

        if (Math.abs(res - target) < cur - target) {
            return;
        } else if (Math.abs(res - target) >= Math.abs(cur - target)) {
            if (Math.abs(res - target) > Math.abs(cur - target)) {
                res = cur;
            } else {
                res = Math.min(cur, res);
            }
        }

        if (location == toppingCosts.length) {
            return;
        }

        closestCostBacktrace(location + 1, toppingCosts, cur, target);
        closestCostBacktrace(location + 1, toppingCosts, cur + toppingCosts[location], target);
        closestCostBacktrace(location + 1, toppingCosts, cur + toppingCosts[location] * 2, target);
    }


    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] p = new int[n + 1];
        int[] w = new int[n + 1];
        int[] neg = new int[n + 1];
        long[] W = new long[n + 1];
        for (int i = 1; i < n; i++) {
            p[i] = boxes[i][0];
            w[i] = boxes[i][1];
            if (i > 1) {
                neg[i] = neg[i - 1] + (p[i - 1] != p[i] ? 1 : 0);
            }
            W[i] = W[i - 1] + w[i];

        }
        // 1.原始
        //j < i
        // f[i] = min(f[j] + neg[j+1,i])+2

        // 2.优化
        // f[i] = min(f[j] + neg[i] - neg[j+1] ) + 2
        // f[i] = min(f[j] - neg[j+1]) + neg[i] +2
        // f[i] = min[g[j]] + neg[i] + 2;

        //  g[j] = f[j] - neg[j+1]
        //  i - j <= MaxBoxes
        //  Wi - Wj <= maxWeight
        //  kede  : j  >= i-maxBoxes
        //          wj >= wi-maxWeight
        //  维持g[j] 的单调性
        //  考虑g[j0] 和 g[j1] j0 < j1
        //  如果g[j0] >= g[j1]  去掉g[j0]  因为i增大，j0会先不满足，而且g[j1]是更优解
        //  如果g[j0] <  g[j1]  g[j0],g[j1]都保留
        //  每次从队列前面取值进行转移计算（min），注意要满足条件

        Deque<Integer> opt = new ArrayDeque<>();
        opt.addLast(0);
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            while (i - opt.peekFirst() > maxBoxes || W[i] - W[opt.peekFirst()] > maxWeight) {
                opt.pollFirst();
            }

            f[i] = g[opt.peekFirst()] + neg[i] + 2;

            if (i != n) {
                g[i] = f[i] - neg[i + 1];
                while (!opt.isEmpty() && g[i] <= g[opt.peekLast()]) {
                    opt.pollLast();
                }
                opt.offerLast(i);
            }
        }
        return f[n];
    }

    public int minOperations(int[] nums1, int[] nums2) {

        if (6 * nums1.length < nums2.length || 6 * nums2.length < nums1.length) return -1;
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        int diff = sum1 - sum2;
        if (diff < 0) {
            diff = -diff;
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        if (diff == 0) {
            return 0;
        }
        int ans = 0;

        // 记录变化量
        int[] count = new int[7];

        for (int num : nums1) count[num - 1]++;
        for (int num : nums2) count[6 - num]++;

        for (int i = 5; i >= 1; i--) {
            int q = count[i];
            if (diff < q * i) {
                ans += diff / i+ (diff % i == 0 ? 0 : 1);
                break;
            } else if (diff > q * i) {
                diff = diff - q * i;
                ans += q;
            } else {
                ans = ans + q;
                break;
            }
        }

        return ans;
    }


    public int maxHeight(int[][] cuboids) {
        // 先确定好长宽高 想要最长一定需要为最长的边作为高
        for (int[] cur : cuboids){
            Arrays.sort(cur);
        }

        // 排好相对顺序，小的在前面，大的在后面
        Arrays.sort(cuboids , Comparator.comparingInt(a -> a[0] + a[1] + a[2]));

        int[] dp = new int[cuboids.length];

        int ans = 0;


        for (int i = 0 ; i < cuboids.length ; i++){
            dp[i] = cuboids[i][2];

            for (int j = 0 ; j < i ; j++){
                if (cuboids[i][0] >= cuboids[j][0] &&
                        cuboids[i][1] >= cuboids[j][1] &&
                        cuboids[i][2] >= cuboids[j][2]){
                    dp[i] = Math.max(dp[i],dp[j] + cuboids[i][2]);
                }
            }

            ans = Math.max(ans,dp[i]);
        }

        return ans;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minOperations(new int[]{5, 6, 4, 3, 1, 2}, new int[]{6, 3, 3, 1, 4, 5, 3, 4, 1, 3, 4});
    }
}
