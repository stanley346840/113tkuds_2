/**
 * 定義單向鏈結串列的類別。
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立一個虛擬頭節點，指向鏈結串列的頭部
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 初始化兩個指針 fast 和 slow，都從虛擬頭節點開始
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 將 fast 指針向前移動 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 同步移動兩個指針，直到 fast 指針到達鏈結串列的末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 現在 slow 指針位於目標節點的前一個位置。
        // 更新它的 next 指針以跳過目標節點。
        slow.next = slow.next.next;

        // 新的頭部是 dummy.next
        return dummy.next;
    }
}