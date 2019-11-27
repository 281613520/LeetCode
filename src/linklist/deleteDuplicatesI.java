package linklist;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: -1 -> 1->1->2->3->3
 * 输出: 1->2->3
 */
public class deleteDuplicatesI {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode pre = result;

        while (pre != null){
            ListNode cur = pre.next;
            if (cur != null && cur.next != null && cur.val == cur.next.val){
                while (cur.next != null){
                    if (cur.val != cur.next.val){
                        pre = pre.next;
                        pre.next = cur.next;
                        break;
                    }
                    cur = cur.next;
                    if (cur.next == null){
                        pre = pre.next;
                        pre.next = null;
                    }

                }
            }else {
                pre = pre.next;
            }

        }

        return result.next;
    }
}
