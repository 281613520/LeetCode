package binarysearch;


/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * 因为是ologn 必然是二分搜
 * 然后就是搜左界和右界
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (nums.length == 0) return result;
        int left = 0;
        int right = nums.length - 1;
        int l = 0;
        int r = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                l = mid;
                r = mid;
                break;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            }
            if (target < nums[mid]) {
                right = mid - 1;
            }
        }

        while(l >= 0){
            if (target == nums[l]){
                result[0] = l;
            }

            if (target != nums[l]){
                break;
            }
            l--;
        }

        while(r < nums.length){
            if (target == nums[r]){
                result[1] = r;
            }

            if (target != nums[r]) {
                break;
            }
            r++;
        }

        return result;
    }

    public int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int left = findLeft(nums, target);
        if (left == -1) {
            return new int[]{-1, -1};
        }
        int right = findRight(nums, target);

        return new int[]{left, right};

    }

    private int findRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return right;
    }

    private int findLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }


}
