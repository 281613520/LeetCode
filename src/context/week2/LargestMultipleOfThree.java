package context.week2;

import java.util.Arrays;

/**
 * 给你一个整数数组 digits，你可以通过按任意顺序连接其中某些数字来形成 3 的倍数，请你返回所能得到的最大的 3 的倍数。
 *
 * 由于答案可能不在整数数据类型范围内，请以字符串形式返回答案。
 *
 * 如果无法得到答案，请返回一个空字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = [8,1,9]
 * 输出："981"
 * 示例 2：
 *
 * 输入：digits = [8,6,7,1,0]
 * 输出："8760"
 * 示例 3：
 *
 * 输入：digits = [1]
 * 输出：""
 * 示例 4：
 *
 * 输入：digits = [0,0,0,0,0,0]
 * 输出："0"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-multiple-of-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestMultipleOfThree {
    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        int sum = 0;
        for (int tmp : digits){
            sum+=tmp;
        }
        if (sum == 0) return "0";
        StringBuilder res = new StringBuilder();
        int flag = sum%3;

        if (flag == 0){
            for (int tmp:digits){
                res.append(tmp);
            }
        }

        if (flag == 1){

        }

        if (flag == 2){

        }

        return res.toString();
    }
}
