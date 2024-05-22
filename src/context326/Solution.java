package context326;

import context.week5.UndergroundSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            int cur = num;
            for (int i = 2; i * i <= num; i++) {
                if (cur % i == 0) {
                    set.add(i);
                    while (cur % i == 0) {
                        cur /= i;
                    }
                }
            }

            if (cur >1){
                set.add(cur);
            }
        }

        return set.size();
    }

    public int minimumPartition(String s, int k) {
            long sum = 0;
            int ans = 0;

        for (char c : s.toCharArray()) {
            int cur = Character.getNumericValue(c);

            if (cur > k) return -1;

            sum = sum*10 + cur;

            if (sum > k){
                ans++;
                sum = cur;
            }
        }


        return ans+1;
    }

    private final static int MX = (int) 1e6;
    private final static int[] primes = new int[78500];

    static {
        var np = new boolean[MX + 1];
        var pi = 0;
        for (var i = 2; i <= MX; ++i) {
            if (!np[i]) primes[pi++] = i;
            for (var j = 0; j < pi; ++j) {
                var p = primes[j];
                if (i * p > MX) break;
                np[i * p] = true;
                if (i % p == 0) break;
            }
        }
        primes[pi++] = MX + 1;
        primes[pi++] = MX + 1; // 保证下面下标不会越界
    }

    public int[] closestPrimes(int left, int right) {
        TreeMap<Integer, Integer> set = new TreeMap<>();

        for (int i = 0; i < primes.length; i++) {
            if (primes[i] != 0){
                set.put(primes[i], i);
            }
        }

        Integer result = set.ceilingKey(left);
        if (result == null) return new int[]{-1,-1};
        int p = -1,q= -1;
        for (int i = set.get(result) ; i+1 < primes.length && primes[i+1] <= right ; i++){
            if (p<0 || primes[i+1] - primes[i] < q-p){
                p=primes[i];
                q=primes[i+1];
            }
        }

        return new int[]{p,q};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.closestPrimes(999998,1000000);
    }
}
