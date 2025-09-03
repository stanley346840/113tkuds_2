/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        // 創建一個虛擬頭節點來簡化操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            // 定義需要交換的兩個節點
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // 執行交換
            first.next = second.next; // 步驟 1
            second.next = first;     // 步驟 2
            prev.next = second;      // 步驟 3

            // 移動 prev 指針到下一對節點的前一個位置
            prev = first;
        }

        return dummy.next;
    }
}