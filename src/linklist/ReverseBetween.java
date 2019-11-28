package linklist;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int[] result = new int[n - m + 1];
        int i = 1 ;
        int j = 0 ;
        ListNode cur = head;
        while (cur != null){
            if (m <= i && i <= n){
                result[n - m - j] = cur.val;
                i++;
                j++;
            }else {
                i++;
            }
            cur = cur.next;
        }

        int a = 1 ;
        int b = 0 ;
        ListNode cur2 = head;
        while (cur2 != null){
            if (m <= a && a <= n){
                cur2.val = result[b];
                a++;
                b++;
            }else {
                a++;
            }
            cur2 = cur2.next;
        }



        return head;
    }
}
