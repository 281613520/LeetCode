package context.week2;

/**
 * 给你一个整数 num，请你找出同时满足下面全部要求的两个整数：
 * <p>
 * 两数乘积等于  num + 1 或 num + 2
 * 以绝对差进行度量，两数大小最接近
 * 你可以按任意顺序返回这两个整数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 8
 * 输出：[3,3]
 * 解释：对于 num + 1 = 9，最接近的两个因数是 3 & 3；对于 num + 2 = 10, 最接近的两个因数是 2 & 5，因此返回 3 & 3 。
 * 示例 2：
 * <p>
 * 输入：num = 123
 * 输出：[5,25]
 * 示例 3：
 * <p>
 * 输入：num = 999
 * 输出：[40,25]
 */
public class ClosestDivisors {
    public int[] closestDivisors(int num) {
        int num1 = num + 1;
        int num2 = num + 2;

        int[] res1 = help(num1);
        int[] res2= help(num2);


        if (Math.abs(res1[0] - res1[1]) > Math.abs(res2[0] - res2[1])){
            return res2;
        }else {
            return res1;
        }
    }

    private int[] help(int num){
        int i = 1;
        int[] res = new int[2];
        int min = Integer.MAX_VALUE;
        while (i*i < num + 2){// 稍微优化一下就好了 原来是i< num  肯定有不合适的值
            if (num%i == 0){
                int cur = num/i;
                int cha = Math.abs(cur - i);
                if (cha < min ){
                    min = cha;
                    res[0] = i;
                    res[1] = num/i;
                }
            }
            i++;
        }
        return res;
    }
 }
