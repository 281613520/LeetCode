package contest319;

import java.util.*;

public class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int lsm = 1;
            for (int j = i; j < nums.length; j++) {
                lsm = lsm * nums[j] / gcd(lsm, nums[j]);
                if (lsm == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        int r = x % y;

        return gcd(y, r);
    }


    public int minimumOperations(TreeNode root) {
        int ans = 0;


        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            int[] nums = new int[size];

            int i = 0;
            while (i < size) {
                TreeNode cur = queue.poll();
                nums[i] = cur.val;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                i++;
            }

            ans += help(nums);
        }
        return ans;
    }

    private int help(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] clone = nums.clone();
        Arrays.sort(clone);
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            map.put(clone[i], i);
        }
        for (int i = 0; i < n; i++) {
            for (;;) {
                int idx = map.get(nums[i]);
                if (idx != i) {
                    ans++;
                    swap(nums, idx, i);
                } else {
                    break;
                }
            }
        }
        return ans;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.subarrayLCM(new int[]{3, 6, 2, 7, 1}, 6);
    }
}