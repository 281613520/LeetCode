package linklist;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: -1 -> 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * <p>
 * <p>
 * 示例 2:
 * -1->1->1
 * <p>
 * 输入: -1->1->1->1->2->3
 * 输出: 2->3
 */
public class DeleteDuplicates {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode p = result;
        while (p != null){
            ListNode cur = p.next;
            if (cur != null && cur.next != null && cur.val == cur.next.val){
                while (cur.next != null){
                    // 相等
                    if (cur.val != cur.next.val){
                        p.next = cur.next;
                        break;
                    }
                    cur = cur.next;
                    // 发现到了最后
                    if (cur.next == null){
                        p.next = null;
                    }
                }
            } else {
                p = p.next;
            }
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(1);
        first.next = second;
        deleteDuplicates(first);
    }
}
