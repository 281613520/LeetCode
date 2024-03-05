package daily;


import banzi.UnionFind;
import com.sun.source.tree.Tree;
import contest316.ListNode;
import context.week5.UndergroundSystem;
import math.TreeNode;

import java.io.PrintWriter;
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
                ans += diff / i + (diff % i == 0 ? 0 : 1);
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
        for (int[] cur : cuboids) {
            Arrays.sort(cur);
        }

        // 排好相对顺序，小的在前面，大的在后面
        Arrays.sort(cuboids, Comparator.comparingInt(a -> a[0] + a[1] + a[2]));

        int[] dp = new int[cuboids.length];

        int ans = 0;


        for (int i = 0; i < cuboids.length; i++) {
            dp[i] = cuboids[i][2];

            for (int j = 0; j < i; j++) {
                if (cuboids[i][0] >= cuboids[j][0] &&
                        cuboids[i][1] >= cuboids[j][1] &&
                        cuboids[i][2] >= cuboids[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }


    public int numDifferentIntegers(String word) {
        int i = 0;
        int j = 0;
        Set<String> nums = new HashSet<>();

        while (i < word.length() && j < word.length()) {
            i = j;
            while (i < word.length() && !Character.isDigit(word.charAt(i))) {
                i++;
            }
            j = i;

            while (j < word.length() && Character.isDigit(word.charAt(j))) {
                j++;
            }

            while (j - i > 1 && word.charAt(i) == '0') {
                i++;
            }
            if (i < word.length() || j < word.length()) {
                nums.add(word.substring(i, j));
            }

        }

        return nums.size();
    }


    public int beautySum(String s) {
        int[][] preSum = new int[26][s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            int cur = s.charAt(i - 1) - 'a';
            for (int j = 0; j < 26; j++) {
                if (cur == j) {
                    preSum[j][i] = preSum[j][i - 1] + 1;
                } else {
                    preSum[j][i] = preSum[j][i - 1];
                }

            }
        }

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < 26; k++) {
                    max = Math.max(max, preSum[k][j] - preSum[k][i]);
                    if (preSum[k][j] - preSum[k][i] != 0) {
                        min = Math.min(min, preSum[k][j] - preSum[k][i]);
                    }
                }

                ans += max - min;

            }
        }

        return ans;
    }

    public boolean canChoose(int[][] groups, int[] nums) {
        int count = groups.length;

        int startLocation = 0;

        for (int[] cur : groups) {
            while (startLocation < nums.length) {
                boolean nextNums = false;
                if (nums[startLocation] == cur[0]) {
                    int i = startLocation;
                    boolean flag = true;
                    if (nums.length - startLocation >= cur.length) {
                        for (int j = 0; j < cur.length && i < nums.length; j++, i++) {
                            if (nums[i] != cur[j]) {
                                flag = false;
                                break;
                            }
                        }
                    } else {
                        flag = false;
                    }
                    if (flag) {
                        nextNums = true;
                        count--;
                        i--;
                        startLocation = i;
                    }
                }
                startLocation++;
                if (nextNums) {
                    break;
                }
            }
        }

        return count == 0;
    }


    public boolean digitCount(String num) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < num.length(); i++) {
            int cur = num.charAt(i) - '0';
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }


        for (int i = 0; i < num.length(); i++) {
            int cur = num.charAt(i) - '0';
            if (map.getOrDefault(i, 0) != cur) {
                return false;
            }
        }

        return true;
    }


    public int reinitializePermutation(int n) {
        int ans = 0;
        int[] perm = new int[n];
        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
            target[i] = i;
        }

        while (true) {
            ans++;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    arr[i] = perm[i / 2];
                } else {
                    arr[i] = perm[n / 2 + (i - 1) / 2];
                }
            }

            perm = arr;

            if (Arrays.equals(perm, target)) {
                break;
            }
        }

        return ans;
    }


    public int fillCups(int[] amount) {

        List<Integer> queue = new ArrayList<>();

        for (int i = 0; i < amount.length; i++) {
            if (amount[i] != 0) {
                queue.add(amount[i]);
            }
        }

        int ans = 0;

        while (queue.size() >= 2) {
            queue.sort(Comparator.comparingInt(o -> o));
            int first = queue.get(0);

            int last = queue.get(queue.size() - 1);

            first--;
            last--;
            ans++;

            queue.remove(0);
            queue.remove(queue.size() - 1);
            if (first > 0) {
                queue.add(first);
            }
            queue.add(last);
        }


        return ans + (queue.size() == 1 ? queue.get(0) : 0);
    }


    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();

        char lastChar = 'a';

        for (int i = 0; i < target.length(); i++) {
            char curTarget = target.charAt(i);

            int dis = Math.abs(curTarget - 'a');

            int nx = dis % 5;
            int ny = dis / 5;

            int preDis = Math.abs(lastChar - 'a');

            int px = preDis % 5;
            int py = preDis / 5;

            lastChar = curTarget;
            if (ny < py) {
                for (int j = 0; j < py - ny; j++) {
                    sb.append("U");
                }
            }

            if (nx < px) {
                for (int j = 0; j < px - nx; j++) {
                    sb.append("L");
                }
            }

            if (ny > py) {
                for (int j = 0; j < ny - py; j++) {
                    sb.append("D");
                }
            }

            if (nx > px) {
                for (int j = 0; j < nx - px; j++) {
                    sb.append("R");
                }
            }


            sb.append("!");

        }


        return sb.toString();
    }


    //20 [8,5,10,8,7,2]
    public int dieSimulator(int n, int[] rollMax) {
        long res = 0;
        int m = 6;
        int[][][] cache = new int[n][m][15];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }

        for (int j = 1; j <= 6; j++) {
            res = (res + dfs(j, 1, n - 1, rollMax, cache)) % 1000000007;
        }

        return (int) res % 1000000007;
    }

    private int dfs(int num, int cnt, int loopTime, int[] rollMax, int[][][] cache) {
        if (loopTime == 0) {
            return 1;
        }
        if (cache[loopTime][num - 1][rollMax[num - 1] - cnt] >= 0)
            return cache[loopTime][num - 1][rollMax[num - 1] - cnt];
        long res = 0;
        for (int i = 1; i <= 6; i++) {
            if (num == i) {
                if (cnt < rollMax[i - 1]) {
                    res += dfs(i, cnt + 1, loopTime - 1, rollMax, cache);
                }
            } else {
                res += dfs(i, 1, loopTime - 1, rollMax, cache);
            }
        }
        cache[loopTime][num - 1][rollMax[num - 1] - cnt] = (int) res % 1000000007;

        return (int) res % 1000000007;
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode preA = list1;
        for (int i = 0; i < a - 1; i++) {
            preA = preA.next;
        }
        ListNode preB = preA;
        for (int i = 0; i < b - a + 2; i++) {
            preB = preB.next;
        }
        preA.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = preB;
        return list1;
    }


    public int longestWPI(int[] hours) {

        int[] preSum = new int[hours.length + 1];

        int ans = 0;

        for (int i = 1; i <= hours.length; i++) {
            if (hours[i - 1] > 8) {
                preSum[i] = preSum[i - 1] + 1;
            } else {
                preSum[i] = preSum[i - 1] - 1;
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        for (int i = 1; i < preSum.length; i++) {
            if (preSum[i] < preSum[queue.peekLast()]) {
                queue.add(i);
            }
        }


        for (int i = preSum.length - 1; i >= 0; i--) {
            while (!queue.isEmpty() && preSum[queue.peekLast()] < preSum[i]) {
                int idx = queue.pollLast();
                ans = Math.max(ans, i - idx);
            }
        }


        return ans;
    }


    public int balancedString(String s) {
        int[] nums = new int[4];

        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            nums[getIdx(curChar)]++;
        }

        int ans = Integer.MAX_VALUE;

        int avg = s.length() / 4;
        int i = 0;

        if (nums[0] == avg && nums[1] == avg && nums[2] == avg && nums[3] == avg) {
            return 0;
        }

        for (int j = 0; j < s.length(); j++) {
            char curChar = s.charAt(j);
            nums[getIdx(curChar)]--;

            while (i <= j && nums[0] <= avg && nums[1] <= avg && nums[2] <= avg && nums[3] <= avg) {
                ans = Math.min(ans, j - i + 1);
                nums[getIdx(s.charAt(i))]++;
                i++;
            }
        }


        return ans;
    }

    int getIdx(char c) {
        if (c == 'Q') {
            return 0;
        }

        if (c == 'W') {
            return 1;
        }

        if (c == 'E') {
            return 2;
        }

        if (c == 'R') {
            return 3;
        }

        return 0;
    }

    public int maxWidthRamp(int[] nums) {
        int ans = 0;

        Deque<Integer> deque = new ArrayDeque<>();

        deque.add(0);

        for (int i = 1; i < nums.length; i++) {
            if (nums[deque.peekLast()] > nums[i]) {
                deque.add(i);
            }
        }

        for (int j = nums.length - 1; j >= 0; j--) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[j]) {
                int idx = deque.pollLast();
                ans = Math.max(ans, j - idx + 1);
            }
        }


        return ans;
    }


    public boolean isGoodArray(int[] nums) {
        int ans = nums[0];
        int i = 1;
        while (i < nums.length) {
            ans = GCD(nums[i], ans);

            if (ans == 1) {
                break;
            }
            i++;
        }

        return ans == 1;
    }

    private int GCD(int max, int min) {
        if (max < min) {
            int tmp = max;
            max = min;
            min = tmp;
        }
        int t = max % min;
        while (t != 0) {
            max = min;
            min = t;
            t = max % min;
        }
        return min;
    }


    public int[] numberOfPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int cur : nums) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        int[] ans = new int[2];

        for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
            int s = kv.getValue();
            ans[0] += s / 2;
            ans[0] += s % 2;
        }

        return ans;
    }


    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] up = new int[m + 1][n + 1];
        int[][] left = new int[m + 1][n + 1];

        int ans = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (grid[i - 1][j - 1] == 1) {
                    up[i][j] = up[i - 1][j] + 1;
                    left[i][j] = left[i][j - 1] + 1;

                    int max = Math.min(up[i][j], left[i][j]);

                    while (left[i - max + 1][j] < max || up[i][j - max + 1] < max) {
                        max--;
                    }

                    ans = Math.max(max, ans);
                }
            }
        }

        return ans * ans;
    }


    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            long val1 = (long) (b[1] + 1) * b[1] * (a[1] - a[0]);
            long val2 = (long) (a[1] + 1) * a[1] * (b[1] - b[0]);
            if (val1 == val2) {
                return 0;
            }
            return val1 < val2 ? 1 : -1;
        });


        for (int[] curClass : classes) {
            pq.add(new int[]{curClass[0], curClass[1]});
        }


        int tmp = extraStudents;

        while (tmp > 0) {
            int[] curClass = pq.poll();

            pq.add(new int[]{curClass[0] + 1, curClass[1] + 1});
            tmp--;
        }

        double avg = 0.0;

        while (!pq.isEmpty()) {
            int[] curClass = pq.poll();

            avg += (double) curClass[0] / curClass[1];
        }

        return avg / classes.length;
    }

    public int movesToMakeZigzag(int[] nums) {
        if (nums.length == 1 || nums.length == 0) return 0;
        int oddMax = 0;
        int evenMax = 0;
        // 偶数
        for (int i = 1; i < nums.length; i += 2) {
            evenMax = helper(nums, evenMax, i);
        }
        // 奇数
        for (int i = 0; i < nums.length; i += 2) {


            if (i == 0) {
                if (nums[0] >= nums[1]) {
                    oddMax += nums[0] - nums[1] + 1;
                }
                continue;
            }
            oddMax = helper(nums, oddMax, i);
        }

        return Math.min(oddMax, evenMax);
    }

    private int helper(int[] nums, int num, int i) {
        if (i == nums.length - 1) {
            if (nums[i] - nums[i - 1] >= 0) {
                num += nums[i] - nums[i - 1] + 1;
            }
            return num;
        }
        int left = nums[i - 1];
        int right = nums[i + 1];
        int cur = nums[i];
        int delta = 0;

        if (left <= cur) {
            delta = cur - left + 1;
        }
        if (right <= cur) {
            delta = Math.max(delta, cur - right + 1);
        }

        num += delta;
        return num;
    }

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();

        String[] ans = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String cur = names[i];
            if (map.containsKey(cur)) {
                int count = map.get(cur) + 1;
                String tmp = cur + "(" + count + ")";
                while (map.containsKey(tmp)) {
                    count++;
                    tmp = cur + "(" + count + ")";
                }
                map.put(tmp, 0);
                map.put(cur, count);
                ans[i] = tmp;
            } else {
                map.put(cur, 0);
                ans[i] = cur;
            }
        }

        return ans;
    }


    public int countSubstrings(String s, String t) {
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                int diff = 0;
                for (int k = 0; i + k < s.length() && j + k < t.length(); k++) {
                    if (s.charAt(i + k) != t.charAt(j + k)) {
                        diff++;
                    }

                    if (diff > 1) {
                        break;
                    } else if (diff == 1) {
                        res++;
                    }
                }
            }
        }


        return res;
    }

    public int countSubstrings2(String s, String t) {
        int res = 0;

        int m = s.length();
        int n = t.length();
        int[][] dpr = new int[m + 1][n + 1];
        int[][] dpl = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dpl[i + 1][j + 1] = s.charAt(i) == t.charAt(j) ? (dpl[i][j] + 1) : 0;
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dpr[i][j] = s.charAt(i) == t.charAt(j) ? (dpr[i + 1][j + 1] + 1) : 0;
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    res += (dpl[i][j] + 1) * (dpr[i + 1][j + 1] + 1);
                }
            }
        }


        return res;
    }


    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }


    public String shortestCommonSupersequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder res = new StringBuilder();

        int i = m;
        int j = n;

        while (i > 0 && j > 0) {
            if (i > 0 && j > 0 && text1.charAt(i - 1) == text2.charAt(j - 1)) {
                res.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j]) {
                res.append(text1.charAt(i - 1));
                i--;
            } else {
                res.append(text2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            res.append(text1.charAt(i - 1));
            i--;
        }
        // s2[0:j-1]均未对lcs做出贡献，属于s2独有，全部保留
        while (j > 0) {
            res.append(text2.charAt(j - 1));
            j--;
        }


        return res.reverse().toString();
    }

    public int countVowelStrings(int n) {
        int[] dp_a = new int[n + 1];
        int[] dp_e = new int[n + 1];
        int[] dp_i = new int[n + 1];
        int[] dp_o = new int[n + 1];
        int[] dp_u = new int[n + 1];
        dp_a[1] = 1;
        dp_e[1] = 1;
        dp_i[1] = 1;
        dp_o[1] = 1;
        dp_u[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp_a[i] = dp_a[i - 1] + dp_e[i - 1] + dp_i[i - 1] + dp_o[i - 1] + dp_u[i - 1];
            dp_e[i] = dp_e[i - 1] + dp_i[i - 1] + dp_o[i - 1] + dp_u[i - 1];
            dp_i[i] = dp_i[i - 1] + dp_o[i - 1] + dp_u[i - 1];
            dp_o[i] = dp_o[i - 1] + dp_u[i - 1];
            dp_u[i] = dp_u[i - 1];
        }


        return dp_a[n] + dp_e[n] + dp_i[n] + dp_o[n] + dp_u[n];
    }


    int[] v;

    int[][] cache;

    public int minScoreTriangulation(int[] values) {
        v = values;
        int n = v.length;
        cache = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }

        return dfsMinScoreTriangulation(0, n - 1);
    }

    private int dfsMinScoreTriangulation(int i, int j) {
        if (i + 1 == j) {
            return 0;
        }

        if (cache[i][j] != -1) return cache[i][j];

        int res = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            res = Math.min(res, dfsMinScoreTriangulation(i, k) + dfsMinScoreTriangulation(k, j) + v[i] * v[j] * v[k]);
        }
        return cache[i][j] = res;

    }


    public int maxAncestorDiff(TreeNode root) {
        return dfsTreeForDiff(root, root.val, root.val);
    }

    private int dfsTreeForDiff(TreeNode node, int min, int max) {
        if (node == null) {
            return 0;
        }

        int diff = Math.max(Math.abs(node.val - min), Math.abs(node.val - max));
        min = Math.min(node.val, min);
        max = Math.max(node.val, max);
        diff = Math.max(diff, dfsTreeForDiff(node.left, min, max));
        diff = Math.max(diff, dfsTreeForDiff(node.right, min, max));


        return diff;
    }


    public int maxSumAfterPartitioning(int[] arr, int k) {
        // 区间内的最大值
        int n = arr.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int max = arr[i - 1];
            for (int j = i - 1; j >= 0 && j >= i - k; j--) {
                dp[i] = Math.max(dp[i], dp[j] + max * (i - j));
                if (j > 0) {
                    max = Math.max(arr[j - 1], max);
                }
            }
        }


        return dp[n];
    }

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][1001];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int diff = nums[i] - nums[j] + 500;
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] == 0 ? 2 : dp[j][diff] + 1);
                ans = Math.max(ans, dp[i][diff]);
            }
        }


        return ans;
    }


    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];

        Arrays.fill(dp, 1000000);
        dp[0] = 0;

        for (int i = 0; i <= n; i++) {
            int curWidth = 0;
            int maxHeight = 0;

            for (int j = i; j > 0; j--) {
                curWidth += books[j][0];

                if (curWidth > shelfWidth) {
                    break;
                }

                maxHeight = Math.max(maxHeight, books[j][1]);

                dp[i + 1] = Math.min(dp[i + 1], maxHeight + dp[j]);

            }
        }

        return dp[n];
    }


    // todo 有更优的解法
    public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            String str = Integer.toBinaryString(i);
            if (!s.contains(str)) {
                return false;
            }
        }

        return true;
    }


    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = time.length;
        int first = time[0];

        int ans = 0;

        map.put(first % 60, 1);

        for (int i = 1; i < n; i++) {
            int cur = time[i] % 60;
            int rest = cur == 0 ? cur : 60 - cur;

            ans += map.getOrDefault(rest, 0);
            map.put(cur, map.getOrDefault(cur, 0) + 1);

        }
        return ans;
    }

    public int[] rearrangeBarcodes(int[] barcodes) {

        Map<Integer, Integer> count = new HashMap<>();
        for (int b : barcodes) {
            if (!count.containsKey(b)) {
                count.put(b, 0);
            }
            count.put(b, count.get(b) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            pq.offer(new int[]{entry.getValue(), entry.getKey()});
        }
        int n = barcodes.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int[] p = pq.poll();
            int cx = p[0], x = p[1];
            if (i == 0 || res[i - 1] != x) {
                res[i] = x;
                if (cx > 1) {
                    pq.offer(new int[]{cx - 1, x});
                }
            } else {
                int[] p2 = pq.poll();
                int cy = p2[0], y = p2[1];
                res[i] = y;
                if (cy > 1) {
                    pq.offer(new int[]{cy - 1, y});
                }
                pq.offer(new int[]{cx, x});
            }
        }
        return res;
    }


    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();

        int m = matrix.length;
        int n = matrix[0].length;

        for (int[] ints : matrix) {
            StringBuilder cur = new StringBuilder();

            for (int j = 0; j < n; j++) {
                cur.append(ints[j] ^ ints[0]);
            }

            String curStr = cur.toString();
            map.put(curStr, map.getOrDefault(curStr, 0) + 1);
        }

        int ans = 0;


        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ans = Math.max(ans, entry.getValue());
        }

        return ans;
    }


    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;

        if (n < d) return -1;
        int[][] dp = new int[d + 1][n];

        // dp[i][j] = min(dp[i-1][k] + fmax(k+1,j))

        for (int i = 0; i <= d; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[0][i] = max;
        }

        for (int i = 1; i <= d; i++) {
            for (int j = i; j < n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i - 1][k - 1] + max, dp[i][j]);
                }
            }
        }

        return dp[d - 1][n - 1];
    }


    public int storeWater(int[] bucket, int[] vat) {
        //枚举蓄水操作次数
        int n = bucket.length;
        int maxK = Arrays.stream(vat).max().getAsInt();

        if (maxK == 0) {
            return 0;
        }


        int res = Integer.MAX_VALUE;

        for (int k = 1; k <= maxK && k < res; k++) {
            int t = 0;
            for (int j = 0; j < n; j++) {
                t += Math.max(0, (vat[j] + k - 1) / k - bucket[j]);
            }

            res = Math.min(res, t + k);
        }
        return res;
    }


    public boolean haveConflict(String[] event1, String[] event2) {
        return !(event1[1].compareTo(event2[0]) < 0 || event2[1].compareTo(event1[0]) < 0);
    }

    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < tiles.length(); i++) {
            count.put(tiles.charAt(i), count.getOrDefault(tiles.charAt(i), 0) + 1);
        }

        Set<Character> set = new HashSet<>(count.keySet());

        return dfs(tiles.length(), count, set);
    }

    private int dfs(int i, Map<Character, Integer> count, Set<Character> set) {
        if (i == 0) {
            return 1;
        }


        int res = 1;
        for (char c : set) {
            if (count.get(c) > 0) {
                count.put(c, count.get(c) - 1);
                res += dfs(i - 1, count, set);
                count.put(c, count.get(c) + 1);
            }
        }

        return res;
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        boolean flag = dfs(root, limit, 0);
        return flag ? root : null;
    }

    private boolean dfs(TreeNode root, int limit, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val + sum >= limit;
        }


        boolean left = dfs(root.left, limit, sum + root.val);
        boolean right = dfs(root.right, limit, sum + root.val);


        if (!left) {
            root.left = null;
        }

        if (!right) {
            root.right = null;
        }

        return left || right;
    }

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        Arrays.sort(id, (a, b) -> values[b] - values[a]);

        int ans = 0, choose = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n && choose <= numWanted; i++) {
            int label = labels[id[i]];

            if (cnt.getOrDefault(label, 0) == useLimit) {
                continue;
            }

            choose++;
            ans += values[id[i]];
            cnt.put(label, cnt.getOrDefault(label, 0) + 1);
        }
        return ans;
    }


    public String oddString(String[] words) {
        Set<int[]> map = new HashSet<>();
        String res = "";
        for (String curWord : words) {
            int[] curInt = new int[curWord.length() - 1];
            for (int i = 1; i < curWord.length(); i++) {
                curInt[0] = curWord.charAt(i) - curWord.charAt(i - 1);
            }

            if (map.contains(curInt)) {
                continue;
            } else {
                map.add(curInt);
                res = curWord;
            }
        }
        return res;
    }


    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            if (x == n - 1 && y == n - 1) {
                return dist[x][y];
            }
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (x + dx < 0 || x + dx >= n || y + dy < 0 || y + dy >= n) { // 越界
                        continue;
                    }
                    if (grid[x + dx][y + dy] == 1 || dist[x + dx][y + dy] <= dist[x][y] + 1) { // 单元格值不为 0 或已被访问
                        continue;
                    }
                    dist[x + dx][y + dy] = dist[x][y] + 1;
                    queue.offer(new int[]{x + dx, y + dy});
                }
            }
        }
        return -1;
    }

    public double[] sampleStats(int[] count) {
        int n = count.length;
        int total = Arrays.stream(count).sum();
        double max = 0;
        double min = 256;
        double mean = 0;
        double median = 0;
        double mode = 0;
        long sum = 0;
        int maxFreq = 0;
        int cnt = 0;

        int left = (total + 1) / 2;
        int right = (total + 2) / 2;
        for (int i = 0; i < n; i++) {
            sum = sum + (long) count[i] * i;

            if (count[i] > maxFreq) {
                maxFreq = count[i];
                mode = i;
            }

            if (count[i] > 0) {
                if (min == 256) {
                    min = i;
                }
                max = i;
            }


            // 累计到达中位数
            if (cnt < right && cnt + count[i] >= right) {
                median += i;
            }

            if (cnt < left && cnt + count[i] >= left) {
                median += i;
            }

            cnt += count[i];
        }

        median = median / 2.0;
        mean = (double) sum / total;


        return new double[]{min, max, mean, median, mode};
    }

    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> seen = new HashSet<Integer>();
        for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
            seen.add(nums[i] + nums[j]);
        }
        return seen.size();
    }


    public int[] vowelStrings(String[] words, int[][] queries) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        int[] preSum = new int[words.length + 1];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (set.contains(word.charAt(0)) && set.contains(word.charAt(word.length() - 1))) {
                if (i == 0) {
                    preSum[i + 1] = 1;
                } else {
                    preSum[i + 1] = preSum[i] + 1;
                }
            } else {
                preSum[i + 1] = preSum[i];
            }

        }


        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] thisQuery = queries[i];
            ans[i] = preSum[thisQuery[1] + 1] - preSum[thisQuery[0]];
        }

        return ans;
    }

    public int[] applyOperations(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                nums[i - 1] = nums[i - 1] * 2;
                nums[i] = 0;
            }
        }

        int[] res = new int[nums.length];

        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                res[j] = nums[i];
                j++;
            }
        }

        return res;
    }


    public int equalPairs(int[][] grid) {
        int res = 0;
        int n = grid.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrixEqual(i, j, grid)) {
                    res++;
                }
            }
        }

        return res;
    }

    private boolean matrixEqual(int row, int col, int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            if (grid[row][i] != grid[i][col]) {
                return false;
            }
        }
        return true;
    }


    public int maxSubarraySumCircular(int[] nums) {
        // 123123
        // 算两个值 一个最小和 一个最大和
        int sum = Arrays.stream(nums).sum();
        int continus = 0;
        int max = Integer.MIN_VALUE;


        for (int i = 0; i < nums.length; i++) {
            int after = continus + nums[i];
            continus = Math.max(after, nums[i]);
            max = Math.max(max, continus);
        }

        continus = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int after = continus + nums[i];
            continus = Math.min(after, nums[i]);
            min = Math.min(min, continus);
        }

        return max > 0 ? Math.max(max, sum - min) : max;

    }


    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());

        int[] indeg = new int[n];
        for (int[] e : relations) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            ++indeg[b];
        }

        Deque<Integer> q = new ArrayDeque<>();
        int[] f = new int[n];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.offer(i);
                f[i] = t;
                ans = Math.max(ans, t);
            }
        }

        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : g[i]) {
                f[j] = Math.max(f[j], f[i] + time[j]);
                ans = Math.max(f[j], ans);

                indeg[j]--;
                if (indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }

        return ans;
    }


    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i]) {
                set.add(fronts[i]);
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < fronts.length; i++) {
            if (!set.contains(fronts[i])) {
                ans = Math.min(fronts[i], ans);
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;

    }

    public int subtractProductAndSum(int n) {
        int m = 1;
        int s = 0;
        while (n != 0) {
            int cur = n % 10;
            n = n / 10;
            m *= cur;
            s += cur;
        }

        return m - s;
    }


    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];


        // f(i) 代表以i为结尾的最大绝对值

        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int res = Math.max(dp[0][0], Math.abs(dp[0][1]));

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + nums[i], nums[i]);
            dp[i][1] = Math.min(dp[i - 1][1] + nums[i], nums[i]);
            res = Math.max(dp[i][0], Math.max(res, Math.abs(dp[i][1])));
        }
        return res;

    }


    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;

        int[][] dp = new int[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k) {
                        continue;
                    }

                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]);
                }
            }
        }

        int res = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp[n - 1][j]);
        }

        return res;
    }


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }

        if (root2 == null) {
            return root1;
        }

        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);

        return merged;
    }


    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        TreeSet<Integer> treeSet = new TreeSet<>();

        int res = Integer.MAX_VALUE;

        for (int j = x; j < n; j++) {
            treeSet.add(nums.get(j - x));

            int cur = nums.get(j);
            // 找当前最接近cur的值

            Integer floor = treeSet.floor(cur);

            if (floor != null) {
                res = Math.min(res, cur - floor);
            }

            Integer ceiling = treeSet.ceiling(cur);

            if (ceiling != null) {
                res = Math.min(res, ceiling - cur);
            }
        }


        return res;
    }

    private static final long MOD = (long) 1e9 + 7;
    private static final int MX = (int) 1e5 + 1;
    private static final int[] omega = new int[MX];

    static {
        for (int i = 2; i < MX; i++)
            if (omega[i] == 0) // i 是质数
                for (int j = i; j < MX; j += i)
                    omega[j]++; // i 是 j 的一个质因子
    }

    public int maximumScore(List<Integer> nums, int k) {
        //先预处理质数分数，然后计算每一个质数分数能够被选择多少次，排序最大的，从最大的开始计算，累乘
        int n = nums.size();
        return 1;
    }


    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int[] diff = new int[n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += reward2[i];
            diff[i] = reward1[i] - reward2[i];
        }

        int res = sum;

        Arrays.sort(diff);

        for (int i = 1; i <= k; i++) {
            res += diff[n - i];
        }

        return res;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        int preSum = 0;
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while (node != null) {
            preSum += node.val;
            map.put(preSum, node);
            node = node.next;
        }

        preSum = 0;
        node = dummy;

        while (node != null) {
            preSum += node.val;
            node.next = map.get(preSum).next;
            node = node.next;
        }

        return dummy.next;
    }


    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length(), m = indices.length;
        Map<Integer, List<Integer>> ops = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            ops.putIfAbsent(indices[i], new ArrayList<>());
            ops.get(indices[i]).add(i);
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ) {
            boolean success = false;
            if (ops.containsKey(i)) {
                for (int pt : ops.get(i)) {
                    if (s.substring(i, Math.min(i + sources[pt].length(), n)).equals(sources[pt])) {
                        success = true;
                        ans.append(targets[pt]);
                        i += sources[pt].length();
                        break;
                    }
                }
            }

            if (!success) {
                ans.append(s.charAt(i));
                i++;
            }
        }

        return ans.toString();
    }

    public int unequalTriplets(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] != nums[j]) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[i] != nums[k] && nums[j] != nums[k]) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    public int unequalTriplets2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int cur : nums) {
            map.merge(cur, 1, Integer::sum);
        }

        int a = 0;
        int b = nums.length;
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans += entry.getValue() * a * (b - a - entry.getValue());
            a += entry.getValue();
        }

        return ans;
    }

    public boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;
        int n = bills.length;

        for (int i = 0; i < bills.length; i++) {
            int cur = bills[i];
            if (cur == 5) {
                five++;
            } else if (cur == 10) {
                if (five == 0) {
                    return false;
                }

                ten++;
                five--;
            } else {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public int numFactoredBinaryTrees(int[] arr) {
        int mod = 1000000007;
        long res = 0;

        Arrays.sort(arr);
        long[] dp = new long[arr.length];//存储每个位置有多少子数组

        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int left = 0, right = i - 1; left <= right; left++) {
                while (right >= left && (long) arr[left] * arr[right] > arr[i]) {
                    right--;
                }

                if (right >= left && (long) arr[left] * arr[right] == arr[i]) {
                    if (left != right) {
                        dp[i] = (dp[i] + (dp[left] * dp[right]) * 2) % mod;
                    } else {
                        dp[i] = (dp[i] + dp[left] * dp[right]) % mod;
                    }
                }
            }

            res = (res + dp[i]) % mod;
        }

        return (int) res;
    }


    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }

        int n = grid.length;
        int[][] indices = new int[n * n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                indices[grid[i][j]][0] = i;
                indices[grid[i][j]][1] = j;
            }
        }

        for (int i = 1; i < n * n; i++) {
            int dx = Math.abs(indices[i][0] - indices[i - 1][0]);
            int dy = Math.abs(indices[i][1] - indices[i - 1][1]);
            if (dx * dy != 2) {
                return false;
            }
        }
        return true;
    }

    public int giveGem(int[] gem, int[][] operations) {
        for (int[] operation : operations) {
            int x = operation[0];
            int y = operation[1];
            int transfer = gem[x] / 2;
            gem[x] -= transfer;
            gem[y] += transfer;
        }
        Arrays.sort(gem);

        return gem[gem.length - 1] - gem[0];
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];

        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }


    public int rob2(int[] nums) {
        int n = nums.length;
        return Math.max(nums[0] + rob1(nums, 2, n - 1), rob1(nums, 1, n));
    }

    // 198. 打家劫舍
    private int rob1(int[] nums, int start, int end) { // [start,end) 左闭右开
        int f0 = 0, f1 = 0;
        for (int i = start; i < end; ++i) {
            int newF = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }


    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Map<Integer, int[]> candidates = new HashMap<>();
        int kx = king[0], ky = king[1];

        for (int[] queen : queens) {
            int qx = queen[0], qy = queen[1];
            int x = qx - kx;
            int y = qy - ky;
            if (x == 0 || y == 0 || Math.abs(x) == Math.abs(y)) {
                int dx = sgn(x), dy = sgn(y);
                int key = dx * 10 + dy;
                if (!candidates.containsKey(key) || candidates.get(key)[2] > Math.abs(x) + Math.abs(y)) {
                    candidates.put(key, new int[]{queen[0], queen[1], Math.abs(x) + Math.abs(y)});
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, int[]> entry : candidates.entrySet()) {
            int[] value = entry.getValue();
            List<Integer> posList = new ArrayList<>();
            posList.add(value[0]);
            posList.add(value[1]);
            ans.add(posList);
        }
        return ans;
    }

    public int sgn(int x) {
        return x > 0 ? 1 : (x == 0 ? 0 : -1);
    }

    public int minCount(int[] coins) {
        int ans = 0;
        for (int coin : coins) {
            ans += coin / 2 + coin % 2;
        }

        return ans;
    }

    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }

        money -= children;
        int cnt = Math.min(money / 7, children);
        money -= cnt * 7;
        children -= cnt;

        if (money > 0 && children == 0) {
            cnt--;
        }

        if (cnt == 1 && money == 3) {
            cnt--;
        }

        return cnt;
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }


    public int maxProfit3(int[] prices) {
        int[][] dp = new int[2 + 2][2];

        for (int i = 0; i <= 2 + 1; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }
        dp[0][0] = Integer.MIN_VALUE / 2;

        for (int price : prices) {
            for (int j = 2 + 1; j > 0; j--) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + price);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - price);
            }
        }
        return dp[2 + 1][0];

    }

    public int maxProfit4(int k, int[] prices) {
        /*int n = prices.length;

        int[][][] dp = new int[n][k+1][2];

        dp[0][0][0] = 0;
        dp[0][0][1] = -prices[0];
        dp[0][1][0] = 0;


        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j - 1][0] - prices[i]);
            }
        }
        return dp[n-1][k][0];*/

        // 注意边界值，直接多给一位能省很多事情 一般初始化很麻烦
        int n = prices.length;
        int[][] dp = new int[k + 1][2];

        for (int i = 0; i <= k; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }
        dp[0][0] = Integer.MIN_VALUE / 2;

        for (int price : prices) {
            for (int j = k; j >= 0; j--) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + price);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - price);
            }
        }
        return dp[k][0];
    }


    public int maxProfit300(int[] prices) {
        /*int n = prices.length;
        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0; // 没有股票
        dp[0][1] = -prices[0]; // 有股票
        dp[0][2] = 0; // 冷冻期

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][2]);
        }

        return Math.max(dp[n - 1][0], dp[n - 1][2]);*/

        int n = prices.length;
        int f0 = 0;
        int f1 = -prices[0];
        int f2 = 0;

        for (int price : prices) {
            int newf0 = Math.max(f0, f1 + price);
            int newf1 = Math.max(f1, f2 - price);
            int newf2 = Math.max(f0, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }

        return Math.max(f0, f2);
    }


    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int num : nums) {
            q.offer(num);
        }
        long ans = 0;
        for (int i = 0; i < k; ++i) {
            int x = q.poll();
            ans += x;
            q.offer((x + 2) / 3);
        }
        return ans;
    }


    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                map.put(nums[i] * nums[j], map.getOrDefault(nums[i] * nums[j], 0) + 1);
            }
        }

        int res = 0;

        for (Integer v : map.values()) {
            res += v * (v - 1) * 4;
        }


        return res;

    }

    static final int MOD_2 = 1000000007;

    public int numRollsToTarget(int n, int k, int target) {
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= target; ++j) {
                for (int x = 1; x <= k; ++x) {
                    if (j - x >= 0) {
                        f[i][j] = (f[i][j] + f[i - 1][j - x]) % MOD_2;
                    }
                }
            }
        }
        return f[n][target];
    }


    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {


        // 找子数组 然后找对应的数字  缺失情况
        // 1.能够快速找到节点种类
        // 2.能够快速计算出缺了哪个
        // 从根节点向下搜索

        int n = parents.length;
        List<Integer>[] children = new List[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }


        for (int i = 1; i < n; i++) {
            children[parents[i]].add(i);
        }

        int[] res = new int[n];

        Arrays.fill(res, 1);

        Set<Integer> geneSet = new HashSet<>();
        boolean[] visited = new boolean[n];


        int ans = 1;
        int node = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                node = i;
                break;
            }
        }


        while (node != -1) {
            dfs2003(node, nums, children, geneSet, visited);

            while (geneSet.contains(ans)) {
                ans++;
            }
            res[node] = ans;
            node = parents[node];
        }

        return res;
    }

    private void dfs2003(int node, int[] nums, List<Integer>[] children, Set<Integer> geneSet, boolean[] visited) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        geneSet.add(nums[node]);
        for (int child : children[node]) {
            dfs2003(child, nums, children, geneSet, visited);
        }
    }

    public int countPoints(String rings) {
        Set<Character>[] sets = new Set[10];
        for (int i = 0; i < 10; i++) {
            sets[i] = new HashSet<>();
        }
        for (int i = 0; i < rings.length(); i += 2) {
            Character color = rings.charAt(i);
            Character index = rings.charAt(i + 1);
            sets[index - '0'].add(color);
        }

        int res = 0;

        for (int i = 0; i < 10; i++) {
            if (sets[i].size() == 3) {
                res++;
            }
        }

        return res;

    }


    public int maxProduct(String[] words) {
        int[] word2int = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                word2int[i] |= 1 << (word.charAt(j) - 'a');
            }
        }

        int ans = 0;

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((word2int[i] & word2int[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }

        return ans;

    }


    public int findTheLongestBalancedSubstring(String s) {
        int ans = 0;
        int count0 = 0;
        int count1 = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int cur = s.charAt(i) - '0';

            if (cur == 0) {
                if (count1 == 0) {
                    count0++;
                } else {
                    count1 = 0;
                    count0 = 1;
                }
            } else if (cur == 1) {
                count1++;
                ans = Math.max(ans, Math.min(count0, count1) * 2);
            }
        }

        return ans;
    }


    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        TreeMap<Long, Integer> set = new TreeMap<>();

        int n = potions.length;

        for (int i = 0; i < potions.length; i++) {
            long key = potions[i];
            if (!set.containsKey(key)) {
                set.put(key, i);
            }
        }

        int[] ans = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            long t = (long) Math.ceil((double) success / (double) spells[i]);
            Long m = set.ceilingKey(t);
            if (m == null) {
                ans[i] = 0;
                continue;
            }
            int location = set.get(m);
            ans[i] = n - location;
        }

        return ans;

    }


    public int[] successfulPairs2(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] ans = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            int cur = spells[i];

            int location = binarySearch2300(success, potions, cur);

            ans[i] = potions.length - location;
        }

        return ans;
    }

    //最左边
    private int binarySearch2300(long success, int[] potions, int cur) {
        int ans = potions.length;
        int l = 0;
        int h = potions.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int target = potions[mid];
            if ((long) target * cur >= success) {
                ans = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }


    public int minSwapsCouples(int[] row) {
        int len = row.length;
        int N = len / 2;

        UnionFind unionFind = new UnionFind(N);

        for (int i = 0; i < len; i += 2) {
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }

        return N - unionFind.getCount();
    }


    public int maximumSum(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int s = 0;
            while (cur > 0) {
                s += cur % 10;
                cur /= 10;
            }

            if (cnt.containsKey(s)) {
                res = Math.max(res, cnt.get(s) + nums[i]);
            }
            cnt.put(s, Math.max(nums[i], cnt.getOrDefault(s, 0)));
        }
        return res;
    }


    public int[] maxSumOfThreeSubarrays1(int[] nums, int k) {
        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
        int sum3 = 0, maxTotal = 0;

        int[] ans = new int[3];

        for (int i = 2 * k; i < nums.length; i++) {
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];

            if (i >= k * 3 - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - 3 * k + 1;
                }
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    maxSum12Idx1 = maxSum1Idx;
                    maxSum12Idx2 = i - 2 * k + 1;
                }

                if (maxSum12 + sum3 > maxTotal) {
                    maxTotal = sum3 + maxSum12;
                    ans[0] = maxSum12Idx1;
                    ans[1] = maxSum12Idx2;
                    ans[2] = i - k + 1;
                    ;
                }
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }

        }

        return ans;
    }

    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int n = nums.length;
        long[][] dp = new long[n + 1][4];
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        for (int i = n - k + 1; i >= 0; i--) {
            for (int j = 1; j < 4; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]);
            }
        }

        int[] ans = new int[3];
        int i = 1, j = 3, idx = 0;
        //倒过来找
        while (j > 0) {
            if (dp[i + 1][j] > dp[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]) {
                //没找到重叠的 就往后挪动
                i++;
            } else {
                // 找到一个不重叠的 就记录下来 同时向后跳k个 且j-- 表示已找到一个了
                ans[idx++] = i - 1;
                i += k;
                j--;
            }
        }
        return ans;
    }

    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = tmp + nums[i];
            tmp = Math.max(cur, nums[i]);
            ans = Math.max(ans, tmp);
        }
        return ans;
    }

    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    public int minDeletion(int[] nums) {
        int ans = 0;
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            //当前是否为偶数
            if ((i - ans) % 2 == 0) {
                // 判断是不是不满足条件，不满足就ans++
                if (nums[i] == nums[i + 1]) {
                    ans++;
                }
            }
        }

        if ((n - ans) % 2 != 0) {
            ans++;
        }
        return ans;
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });


        // dp
        // 以i为结尾的信封 最长的套娃是
        //int[] dp = new int[n]; yidingchaoshi
        // 考虑f【j】 长度为j的子序列中，的最小值
        // 这样遇到比他大的 就能直接接在后面
        // 遇到 比最后一个小的 就二分找前面的  找到恰好 f【i】< h <= f[i+1]的值，并更新掉i+1
        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; i++) {
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(envelopes[i][1]);
            } else {
                int index = bs(f, num);
                f.set(index, num);
            }
        }

        return f.size();
    }

    private int bs(List<Integer> f, int num) {
        int l = 0;
        int r = f.size() - 1;

        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (f.get(mid) < num) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    // dp + 二分
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;

        List<Integer> dp = new ArrayList<>();

        dp.add(nums[0]);

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > dp.get(dp.size() - 1)) {
                dp.add(num);
            } else {
                int index = bs(dp, num);
                dp.set(index, num);
            }
        }
        return dp.size();

    }


    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i]);
        }


        return ans;
    }


    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>() {{
            put("&quot;", "\"");
            put("&apos;", "'");
            put("&amp;", "&");
            put("&gt;", ">");
            put("&lt;", "<");
            put("&frasl;", "/");
        }};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char cur = text.charAt(i);
            if (cur == '&') {
                int j = i + 1;
                while (j < text.length() && j - i < 6 && text.charAt(j) != ';') j++;
                String sub = text.substring(i, Math.min(j + 1, text.length()));
                if (map.containsKey(sub)) {
                    sb.append(map.get(sub));
                    i = j;
                } else {
                    sb.append(cur);
                }
            } else {
                sb.append(cur);
            }
        }

        return sb.toString();
    }


    public int pseudoPalindromicPaths(TreeNode root) {
        int[] count = new int[10];
        return dfs1457(root, count);
    }

    private int dfs1457(TreeNode root, int[] count) {
        if (root == null) {
            return 0;
        }
        count[root.val]++;
        int res = 0;
        if (root.left == null && root.right == null) {
            if (isPseudoPalindrome(count)) {
                res = 1;
            } else {
                res = dfs1457(root.left, count) + dfs1457(root.right, count);
            }
        }
        count[root.val]--;
        return res;
    }

    private boolean isPseudoPalindrome(int[] count) {
        int odd = 0;
        for (int value : count) {
            if (value % 2 == 1) {
                odd++;
            }
        }
        return odd <= 1;
    }


    public int uniqueLetterString(String s) {
        int ans = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int l = i;
            int r = i;
            while (l > 0 && s.charAt(l - 1) != s.charAt(i)) l--;
            while (r < n - 1 && s.charAt(r + 1) != s.charAt(i)) r++;
            ans += (i - l + 1) * (r - i + 1);
        }
        return ans;
    }


    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] count1 = new int[26], count2 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }


        for (int i = 0; i < 26; i++) {
            if ((count1[i] == 0 && count2[i] > 0) || (count1[i] > 0 && count2[i] == 0)) {
                return false;
            }
        }


        Arrays.sort(count1);
        Arrays.sort(count2);


        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }

        return true;

    }


    public int minReorder(int n, int[][] connections) {
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<int[]>();
        }
        for (int[] edge : connections) {
            e[edge[0]].add(new int[]{edge[1], 1});
            e[edge[1]].add(new int[]{edge[0], 0});
        }
        return dfs1466(0, -1, e);
    }

    private int dfs1466(int cur, int parent, List<int[]>[] e) {
        int res = 0;
        for (int[] dest : e[cur]) {
            int point = dest[0];
            int direction = dest[1];
            if (parent != point) {
                res += direction + dfs1466(point, cur, e);
            }
        }
        return res;
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        // 1 2 3 4 5
        long[] dp = new long[n + 1];
        // dp[i] = max(dp[i-1],dp[start] + end - start + tip)
        List<int[]>[] preProcess = new List[n + 1];

        for (int[] ride : rides) {
            int start = ride[0];
            int end = ride[1];
            int tip = ride[2];
            if (preProcess[end] == null) {
                preProcess[end] = new ArrayList<>();
            }
            preProcess[end].add(new int[]{start, end - start + tip});
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (preProcess[i] != null) {
                for (int[] process : preProcess[i]) {
                    dp[i] = Math.max(dp[i], dp[process[0]] + process[1]);
                }
            }
        }
        return dp[n];
    }


    public int nextBeautifulNumber(int n) {
        //1 打表打出来
        //2 硬写 100000

        for (int i = n + 1; i <= 1224444; ++i) {
            if (isBalance(i)) {
                return i;
            }
        }
        return -1;

    }

    private boolean isBalance(int x) {
        int[] count = new int[10];
        while (x > 0) {
            count[x % 10]++;
            x /= 10;
        }
        for (int d = 0; d < 10; ++d) {
            if (count[d] > 0 && count[d] != d) {
                return false;
            }
        }
        return true;
    }


    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;


    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;

            for (int i = 0; i < n; i++) {
                Node f = queue.poll();
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }

                if (i != 0) {
                    last.next = f;
                }
                last = f;
            }
        }

        return root;
    }


    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();

        if (s.length() < 10) {
            return ans;
        }

        Map<String, Integer> map = new HashMap<>();

        for (int i = 10; i <= s.length(); i++) {
            String tmp = s.substring(i - 10, i);
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            if (map.get(tmp) == 2) {
                ans.add(tmp);
            }
        }
        return ans;
    }


    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int res = 0;
        int n = nums.length;

        int l = 0;
        while (l < nums.length) {
            if (nums[l] > threshold || nums[l] % 2 != 0) {
                l++;
                continue;
            }

            int start = l;
            l++;
            while (l < n && nums[l] <= threshold && nums[l] % 2 != nums[l - 1] % 2) {
                l++;
            }

            res = Math.max(res, l - start);
        }

        return res;
    }

    public int longestAlternatingSubarray2(int[] nums, int threshold) {
        int res = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0 && nums[i] <= threshold) {
                for (int j = i; j < n; j++) {
                    if (nums[j] <= threshold) {
                        if (i == j) {
                            res = Math.max(res, 1);
                        } else if (nums[j - 1] % 2 != nums[j] % 2) {
                            res = Math.max(res, j - i + 1);
                        } else {
                            break;
                        }

                    }
                }
            }
        }

        return res;
    }

    public int[] secondGreaterElement(int[] nums) {
        //单调栈
        int n = nums.length;
        int[] ans = new int[n];

        Arrays.fill(ans, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[0] < nums[i]) {
                ans[pq.poll()[1]] = nums[i];
            }


            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                pq.offer(new int[]{nums[stack.peek()], stack.peek()});
                stack.pop();
            }

            stack.push(i);
        }


        return ans;
    }

    public String makeSmallestPalindrome(String s) {

        int i = 0, j = s.length() - 1;
        char[] arr = s.toCharArray();

        while (i <= j) {
            if (arr[i] != arr[j]) {
                arr[i] = arr[j] = (char) Math.min(arr[i], arr[j]);
            }
            i++;
            j--;
        }

        return new String(arr);
    }


    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int cnt = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> list = new ArrayList<>();
            List<Integer> valList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
                list.add(cur);

                valList.add(cur.val);
            }

            Collections.reverse(valList);

            if (cnt % 2 == 1) {
                for (int i = 0; i < size; i++) {
                    list.get(i).val = valList.get(i);
                }
            }
            cnt++;
        }

        return root;
    }


    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node.next != null) {
            node.next = new ListNode(gcd(node.val, node.next.val), node.next);
            node = node.next.next;
        }

        return head;

    }


    public int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }


    public int numberOfBoomerangs(int[][] points) {
        int res = 0;

        for (int[] from : points) {
            Map<Integer, Integer> cache = new HashMap<>();
            for (int[] to : points) {
                int dis = (from[0] - to[0]) * (from[0] - to[0]) + (from[1] - to[1]) * (from[1] - to[1]);
                cache.put(dis, cache.getOrDefault(dis, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> integerIntegerEntry : cache.entrySet()) {
                int num = integerIntegerEntry.getValue();
                res += num * (num - 1);
            }
        }

        return res;
    }

    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, dictionary);

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i <= s.length(); i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1; j >= 0; j--) {
                if (set.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[s.length()];

    }


    public int splitArray(int[] nums, int m) {

        int n = nums.length;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k]));
                }
            }
        }

        return dp[n][m];
    }


    public int stoneGameVI(int[] a, int[] b) {
        int n = a.length;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        Arrays.sort(ids, (i, j) -> a[j] + b[j] - a[i] - b[i]);
        int diff = 0;
        for (int i = 0; i < n; i++) {
            diff += i % 2 == 0 ? a[ids[i]] : -b[ids[i]];
        }
        return Integer.compare(diff, 0);
    }


    public int stoneGameVII(int[] stones) {
        int length = stones.length;
        int[] preSum = new int[length + 1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }

        int[][] mem = new int[length][length];

        return dfs1690(mem, preSum, 0, length - 1);
    }

    private int dfs1690(int[][] mem, int[] preSum, int i, int j) {
        if (i >= j) {
            return 0;
        }

        if (mem[i][j] != 0) {
            return mem[i][j];
        }

        // 选i 和 选j
        int res = Math.max(preSum[j + 1] - preSum[i + 1] - dfs1690(mem, preSum, i + 1, j), preSum[j] - preSum[i] - dfs1690(mem, preSum, i, j - 1));
        mem[i][j] = res;

        return res;
    }


    // dp
    public int stoneGameVII2(int[] stones) {
        // dp[i,j] = max(sum[j+1] - sum[i+1] - dp[i+1,j],sum[j]- sum[i] - dp[i,j-1])
        int n = stones.length;
        int[][] dp = new int[n][n];


        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(preSum[j + 1] - preSum[i + 1] - dp[i + 1][j], preSum[j] - preSum[i] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] table = new int[26];
        int num = 1;
        for (int i = 0; i < 26; i++) {
            table[i] = num;
            num++;
        }

        for (int i = 0; i < chars.length(); i++) {
            char cur = chars.charAt(i);
            table[cur - 'a'] = vals[i];
        }


        int ans = 0;
        int dp = 0;
        for (char c : s.toCharArray()) {
            dp = Math.max(dp, 0) + table[c - 'a'];
            ans = Math.max(ans, dp);
        }


        return ans;

    }


    public int maxResult(int[] nums, int k) {
        int n = nums.length, idx = 0;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            int max = dp[i - 1];
            for (int j = Math.max(i - k, idx); j < i - 1; j++) {
                if (max < dp[j]) {
                    max = dp[j];
                    idx = j;
                } else if (max == dp[j]) {
                    idx = j;
                }
            }
            dp[i] = max + nums[i];
        }
        return dp[n - 1];
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if (root == null) {
            return levelOrder;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                tmp.add(node.val);
            }
            levelOrder.add(0, tmp);

        }
        return levelOrder;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null) {
            if (root.val < p.val && root.val < q.val) {
                return lowestCommonAncestor2(root.right, p, q);
            } else if (root.val > p.val && root.val > q.val) {
                return lowestCommonAncestor2(root.left, p, q);
            } else {
                return root;
            }
        }
        return root;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if (root == null) {
            return levelOrder;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        boolean isLeft = true;


        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (isLeft) {
                    tmp.add(node.val);
                } else {
                    tmp.add(0, node.val);
                }


            }
            levelOrder.add(tmp);
            isLeft = !isLeft;

        }
        return levelOrder;
    }

    public List<Integer> postorder(Node2 root) {
        List<Integer> res = new ArrayList<>();
        dfs590(root, res);
        return res;

    }

    private void dfs590(Node2 root, List<Integer> res) {
        if (root == null) {
            return;
        }

        for (Node2 child : root.children) {
            dfs590(child, res);
        }
        res.add(root.val);
    }


    class Node2 {
        public int val;
        public List<Node2> children;

        public Node2() {
        }

        public Node2(int _val) {
            val = _val;
        }

        public Node2(int _val, List<Node2> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    private Map<Integer, Integer> nodeMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            nodeMap.put(inorder[i], i);
        }

        return dfs105(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode dfs105(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }

        int root = preorderLeft;
        int index_inorder = nodeMap.get(preorder[root]);

        TreeNode treeNode = new TreeNode();
        treeNode.val = preorder[root];

        int size_left_subtree = index_inorder - inorderLeft;

        treeNode.left = dfs105(preorder, inorder, preorderLeft + 1, preorderLeft + size_left_subtree, inorderLeft, index_inorder - 1);
        treeNode.right = dfs105(preorder, inorder, preorderLeft + size_left_subtree + 1, preorderRight, index_inorder + 1, inorderRight);
        return treeNode;
    }


    int[] nums;

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> treenode = new ArrayList<>();
        dfs2478(treenode, root);

        List<List<Integer>> ans = new ArrayList<>();

        queries.forEach(query -> {
            List<Integer> list = new ArrayList<>();
            list.add(left(nums, queries.get(0)));
            list.add(right(nums, queries.get(1)));
            ans.add(list);
        });

        return ans;
    }


    private int left(int[] nums, Integer target) {
        int l = -1, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (nums[mid] > target) r = mid - 1;
            else l = mid;
        }
        return l == -1 ? -1 : nums[l];
    }

    private int right(int[] nums, Integer target) {

        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l == nums.length ? -1 : nums[l];
    }


    private void dfs2478(List<Integer> treenode, TreeNode root) {
        if (root == null) {
            return;
        }

        dfs2478(treenode, root.left);
        treenode.add(root.val);
        dfs2478(treenode, root.right);
    }

    int ans = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null)
            return 0;

        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }

        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }


    int ans2073 = 0;

    public int minIncrements(int n, int[] cost) {
        dfs2673(1, cost);
        return ans2073;
    }

    private int dfs2673(int n, int[] cost) {
        if (n > cost.length) {
            return 0;
        }

        int left = dfs2673(2 * n - 1, cost);
        int right = dfs2673(2 * n, cost);
        ans2073 += Math.abs(left - right);

        return cost[n - 1] + Math.max(left, right);
    }


    public boolean validPartition(int[] nums) {
        int n = nums.length;

        boolean[] dp = new boolean[n + 1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            if (i >= 2) {
                dp[i] = dp[i - 2] && checkRule1(nums, i);
            }

            if (i >= 3) {
                dp[i] = dp[i] || (dp[i - 3] && checkRule2(nums, i));
            }
        }

        return dp[n];
    }

    private boolean checkRule2(int[] nums, int i) {
        return (nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2]) || ((nums[i - 2] + 1 == nums[i - 1]) && (nums[i - 1] + 1 == nums[i]));
    }

    private boolean checkRule1(int[] nums, int location) {
        return nums[location] == nums[location - 1];
    }


    int n = 10001; // 要判断的最大数字
    boolean[] isPrime = new boolean[n + 1];

    private void eratosthenesPrime() {

        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public long countPaths(int n, int[][] edges) {

        long res = 0;
        eratosthenesPrime();

        List<Integer>[] G = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            G[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int i = edge[0], j = edge[1];
            G[i].add(j);
            G[j].add(i);
        }


        List<Integer> seen = new ArrayList<>();
        long[] count = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!isPrime[i]) {
                continue;
            }

            long cur = 0;
            for (int j : G[i]) {
                if (isPrime[j]) {
                    continue;
                }
                if (count[j] == 0) {
                    seen.clear();
                    dfs2867(G, seen, j, i);
                    long cnt = seen.size();
                    for (int k : seen) {
                        count[k] = cnt;
                    }
                }

                res += cur * count[j];
                cur += count[j];

            }

            res += cur;
        }


        return res;
    }

    private void dfs2867(List<Integer>[] g, List<Integer> seen, int i, int pre) {
        seen.add(i);
        for (Integer j : g[i]) {
            if (j != pre && !isPrime[j]) {
                dfs2867(g, seen, j, i);
            }
        }
    }


    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        return 1;
    }


    public int countPaths2(int n, int[][] roads) {
        long[][] g = new long[n][n];

        for (int i = 0 ;i < n;i++){
            Arrays.fill(g[i],Long.MAX_VALUE/2);
        }
        for (int[] e : roads) {
            int a = e[0], b = e[1], c = e[2];
            g[a][b] = g[b][a] = c;
        }

        // 起始先将所有的点标记为「未更新」和「距离为正无穷」
        boolean[] vis = new boolean[n];
        long[] dist = new long[n];
        int[] ways = new int[n];
        Arrays.fill(dist, Long.MAX_VALUE/2);
        // 只有起点最短距离为 0
        dist[0] = 0;
        ways[0] = 1;

        // 有多少个点就迭代多少次
        for (int k = 0; k < n; k++) {
            // 每次找到「最短距离最小」且「未被更新」的点 t
            int x = -1;
            for (int i = 0; i < n; i++) {
                if (!vis[i] && (x == -1 || dist[i] < dist[x])) x = i;
            }
            // 标记点 t 为已更新
            vis[x] = true;
            // 用点 t 的「最小距离」更新其他点
            for (int y = 0; y < n; y++) {
                long newDis = dist[x] + g[x][y];
                if (newDis < dist[y]){
                    dist[y] = newDis;
                    ways[y] = ways[x];
                }else if (newDis == dist[y]){
                    ways[y] = (ways[x] + ways[y])% 1000000007;
                }

            }
        }

        return ways[n - 1];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.countPaths(5, new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}});
    }
}