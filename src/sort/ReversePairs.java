package sort;


/**
 * 面试题51. 数组中的逆序对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 * 归并排序
 */
public class ReversePairs {
    static int res = 0;
    public static int reversePairs(int[] nums) {

        int left = 0;
        int right = nums.length;
        int mid = (left + right) /2;

        // 左边
        mergeSort(nums,left,mid);

        // 右边
        mergeSort(nums,mid + 1 , right);

        // 整个一起排
        mergeSort(nums,left,right);
        return res;
    }

    private static void mergeSort(int[] nums , int left , int right){
        if (left >= right) return;

        int mid = (left + right) /2;

        mergeSort(nums,left,mid);
        mergeSort(nums,mid + 1,right);

        int i = left;
        int j = mid + 1;

        int[] tmpnums = new int[right - left + 1];
        int k = 0;

        while (i <= mid && j <= right){
            if (nums[i] > nums[j]){
                res++;

                j++;
            } else {
                i++;
            }
        }
    }

}
