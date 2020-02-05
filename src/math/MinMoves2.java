package math;

import java.util.Arrays;

/**
 * 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。
 *
 * 例如:
 *
 * 输入:
 * [1,2,3]
 *
 * 输出:
 * 2
 *
 * 思路： 寻找中间数  设这个中间数为m m - a + b - m = b - a  也就是需要挪b-a次 这样子首尾比较就好了  也不需要寻找中间数了
 *
 * 说明：
 * 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
 */
public class MinMoves2 {
    public int minMoves2(int[] nums) {
        int i=0,j=nums.length-1;
        int ret=0;
        Arrays.sort(nums);
        while(i<j){
            ret+=nums[j]-nums[i];
            i++;
            j--;
        }
        return ret;
    }
}
