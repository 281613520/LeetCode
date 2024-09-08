package banzi;

import java.util.Arrays;

public class DP {
    public int countDigitOne(int n) {
        char[] s = String.valueOf(n).toCharArray();
        int m = s.length;

        int[][] mem = new int[m][m];
        for (int[] ints : mem) {
            Arrays.fill(ints, -1);
        }

        return dfs233(0,0,true,s,mem);
    }

    private int dfs233(int i, int cnt, boolean isLimit, char[] s, int[][] mem) {
        if (i == s.length) {
            return cnt;
        }

        if (!isLimit && mem[i][cnt] != -1) {
            return mem[i][cnt];
        }

        int res = 0;

        int up = isLimit? s[i] - '0':9;

        for (int d = 0 ; d <= up ; d++) {
            // (d == 1 ? 1 : 0) 记录1的个数
            res += dfs233(i+1,cnt + (d == 1 ? 1 : 0),isLimit && d == up,s,mem);
        }

        if (!isLimit ){
            mem[i][cnt] = res;
        }
        return res;
    }
}
