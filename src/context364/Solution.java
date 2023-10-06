package context364;

import java.util.List;
import java.util.Stack;

public class Solution {
    public String maximumOddBinaryNumber(String s) {
        int count_1 = 0;
        int count_0 = 0;
        int n = s.length();
        for (int i = 0 ; i< s.length() ; i++){
            if (s.charAt(i) == '0'){
                count_0++;
            }else {
                count_1++;
            }
        }

        StringBuilder sb= new StringBuilder();
        if(count_1==1){
            for(int i=0;i<n-1;i++){
                sb.append('0');
            }
            sb.append('1');
            return sb.toString();
        }
        if(count_1>1){
            for(int j=0;j<count_1-1;j++){
                sb.append('1');
            }
            for(int k=0;k<n-count_1;k++){
                sb.append('0');
            }
            sb.append('1');
            return sb.toString();
        }
        return " ";
    }

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        // 遍历所有高度 计算最大值
        long res = 0;
        for (int i = 0 ; i < n ; i++){
            int curMax = maxHeights.get(i);
            // 向左计算max 递减
            long left = 0;
            int flag = curMax;

            if (i != 0){
                for (int j = i-1 ; j>= 0 ;j--){
                    if (maxHeights.get(j) <= flag){
                        left += maxHeights.get(j);
                        flag = maxHeights.get(j);
                    }else {
                        left += flag;

                    }
                }
            }

            // 向右计算max之和 递减
            flag = curMax;
            long right = 0;
            if (i != n-1){
                for (int j = i+1 ; j < n;j++){
                    if (maxHeights.get(j) <= flag){
                        right += maxHeights.get(j);
                        flag = maxHeights.get(j);
                    }else {
                        right += flag;

                    }
                }
            }
            // 计算最终结果
            res = Math.max(left + right + curMax,res);
        }

        return res;
    }

    public long maximumSumOfHeights2(List<Integer> maxHeights) {
        if (maxHeights.size() ==0){
            return maxHeights.get(0);
        }
        int[] a = maxHeights.stream().mapToInt(i -> i).toArray();
        int n = maxHeights.size();
        // 遍历所有高度 计算最大值
        long res = 0;

        long[] left = new long[n];

        long[] right = new long[n];
        // 先构建left
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        long sum = 0;
        for (int i = 0 ; i < n ; i++) {
            int x = maxHeights.get(i);
            // 单调增
            while (stack.peek() != -1 && x < maxHeights.get(stack.peek())) {
                int j = stack.pop();
                sum -= (long) maxHeights.get(j) * (j - stack.peek());
            }
            sum += (long) x * (i - stack.peek());
            left[i] = sum;
            stack.push(i);
        }


        stack.clear();
        stack.push(n);
        sum = 0;
        // 构建right
        for (int i = n - 1; i >= 0; i--) {
            int x = a[i];
            while (stack.peek() != n && x < a[stack.peek()]) {
                int j = stack.pop();
                sum -= (long) a[j] * (stack.peek() - j); // 撤销之前加到 sum 中的
            }
            sum += (long) x * (stack.peek() - i); // 从 i 到 st.peek()-1 都是 x
            right[i] = sum;
            stack.push(i);
        }

        for (int i = 1 ; i < n -1 ; i++){
            res = Math.max(left[i] + right[i+1],res);
        }
        return res;
    }
}
