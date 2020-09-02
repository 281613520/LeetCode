package linklist;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null ) return null;
        if(head.next == null) return head;
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode cur = result;
        ListNode node = head;
        while(node != null && node.next != null){
            ListNode tmp = node.next;
            node.next = node.next.next;
            tmp.next = node;
            cur.next = tmp;
            cur = node;
            node = node.next;
        }
        return result.next;
    }
}
