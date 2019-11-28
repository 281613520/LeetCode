package linklist;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: -1->1->2->2->4->3->5
 */
public class Partition {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode flag1 = new ListNode(-1);
        ListNode flag2 = new ListNode(-1);
        ListNode first = flag1;
        ListNode second = flag2;
        while (head != null){
            if (head.val < x){
                first.next = head;
                head = head.next;
                first= first.next;
                first.next = null;
            }else {
                second.next = head;
                head = head.next;
                second= second.next;
                second.next = null;
            }
        }
        first.next = flag2.next;

        return flag1.next;
    }
}
