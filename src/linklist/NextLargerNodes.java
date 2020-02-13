package linklist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class NextLargerNodes {
    public static int[] nextLargerNodes(ListNode head) {
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int[] nums = new int[list.size()];
        int index = 0;
        while (index < nums.length) {
            int cv = list.get(index);
            for (int i = index + 1; i < nums.length; i++) {
                if (list.get(i) > cv) {
                    nums[index] = list.get(i);
                    break;
                }
            }
            index++;
        }

        return nums;
    }

    // d单调栈
    public static int[] nextLargerNodes2(ListNode head) {
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int[] nums = new int[list.size()];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            int val = list.get(i);
            while (!stack.isEmpty()&& list.get(stack.peek()) < val){
                int index = stack.pop();
                nums[index] = val;
            }
            stack.push(i);
        }
        return nums;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = null;
        nextLargerNodes(l1);
    }
}
