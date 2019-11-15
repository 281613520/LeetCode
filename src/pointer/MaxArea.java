package pointer;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
public class MaxArea {
    public int maxArea(int[] height) {
        if (height.length == 0) return 0;
        int result = Integer.MIN_VALUE;
        for (int i = 0 ; i < height.length ; i++){
            for (int j = i+1 ; j <height.length ; j++ ){
                int tmp = (j - i) * Math.min(height[i],height[j]);
                result = Math.max(tmp,result);
            }
        }
        return result;
    }

    public int maxArea2(int[] height) {
        if (height.length == 0) return 0;
        int result = Integer.MIN_VALUE;
        int i = 0 ;
        int j = height.length - 1;
        while (i <= j){
            result = Math.max(result,(j - i) * Math.min(height[i],height[j]));
            if (height[i] < height[j]) i++;
            else j--;
        }
        return result;
    }
}
