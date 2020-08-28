package linklist;

/**
 * 19. 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode f = head;
        ListNode s = head;
        for (int i = 0; i < n; i++) {
            f = f.next;
        }
        if(f == null) {
            return head = head.next;
        }
        while(f.next!=null) {
            f = f.next;
            s = s.next;
        }
        s.next = s.next.next;

        return head;
    }
}
