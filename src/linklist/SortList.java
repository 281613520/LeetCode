package linklist;

/**
 *
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = new ListNode(-1);
        merge(result,head);
        return result.next;
    }

    private void merge(ListNode head, ListNode cur) {

    }
}
