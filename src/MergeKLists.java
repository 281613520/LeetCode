public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode result = merge(lists[0],lists[1]);
        for (int i = 2 ; i < lists.length ; i++){
            result = merge(result,lists[i]);
        }
        return result;
    }
    private ListNode merge(ListNode node1,ListNode node2){
        ListNode head = new ListNode(0);
        ListNode res = head;

        while(node1 != null && node2 != null ){
            if (node1.val <= node2.val){
                res.next = node1;
                node1 = node1.next;
                res = res.next;
            }else {
                res.next = node2;
                node2 = node2.next;
                res = res.next;
            }
        }
        res.next = node1==null? node2:node1;
        return head.next;
    }
}
