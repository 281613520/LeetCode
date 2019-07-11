public class Search {
    public static int search(int[] nums, int target) {
        int left  = 0;
        int right =  nums.length - 1;
        while (left <= right){
            int mid = (left + right)/2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] < nums[right]){
                if (nums[mid] < target && nums[right] >= target){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }

            }else {
                if (nums[mid] > target && nums[left] <= target){
                    right = mid - 1;
                }else {
                    left = mid +1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,3};
        System.out.println(search(nums,3));
    }
}
