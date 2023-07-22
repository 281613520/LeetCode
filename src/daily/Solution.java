package daily;


import contest316.ListNode;
import context.week5.UndergroundSystem;
import org.omg.PortableInterceptor.INACTIVE;

import javax.crypto.MacSpi;
import java.awt.datatransfer.StringSelection;
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
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            ans += entry.getValue() * a * (b-a-entry.getValue());
            a+=entry.getValue();
        }

        return ans;
    }

    public boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;
        int n = bills.length;

        for (int i = 0 ; i < bills.length;i++){
            int cur = bills[i];
            if (cur == 5){
                five ++;
            }else if (cur == 10){
                if (five == 0){
                    return false;
                }

                ten++;
                five--;
            }else {
                if (ten>0 && five > 0){
                    ten--;
                    five--;
                }else if (five >= 3){
                    five -= 3;
                }else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.oddString(new String[]{"aaa", "bob", "ccc", "ddd"});
    }
}
