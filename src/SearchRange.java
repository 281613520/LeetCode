public class SearchRange {
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (nums.length == 0) return result;
        int left = 0;
        int right = nums.length - 1;
        int l = 0;
        int r = 0;
        while (left <= right){
            int mid = (left + right) /2 ;
            if (target == nums[mid]){
                l = mid;
                r = mid;
                break;
            }
            if (target > nums[mid]){
                left = mid + 1;
            }
            if (target < nums[mid]){
                right = mid -1;
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

            if (target != nums[r]){
                break;
            }
            r++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        searchRange(nums,1);
    }
}
