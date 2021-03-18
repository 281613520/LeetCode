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

/**
 *
 * 补充go语言解法
 * func reverseBetween(head *ListNode, left, right int) *ListNode {
 *     // 设置 dummyNode 是这一类问题的一般做法
 *     dummyNode := &ListNode{Val: -1}
 *     dummyNode.Next = head
 *     pre := dummyNode
 *     for i := 0; i < left-1; i++ {
 *         pre = pre.Next
 *     }
 *     cur := pre.Next
 *     for i := 0; i < right-left; i++ {
 *         next := cur.Next
 *         cur.Next = next.Next
 *         next.Next = pre.Next
 *         pre.Next = next
 *     }
 *     return dummyNode.Next
 * }
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
