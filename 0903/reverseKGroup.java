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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev_group = dummy;
        
        while (true) {
            // 檢查是否還有完整的 k 個節點
            ListNode start_of_group = prev_group.next;
            ListNode end_of_group = start_of_group;
            int count = 0;
            while (end_of_group != null && count < k) {
                end_of_group = end_of_group.next;
                count++;
            }

            // 如果節點不足 k 個，結束迴圈
            if (count < k) {
                break;
            }

            // 執行反轉操作
            ListNode current = start_of_group;
            ListNode prev_node = end_of_group;
            while (current != end_of_group) {
                ListNode next_node = current.next;
                current.next = prev_node;
                prev_node = current;
                current = next_node;
            }

            // 重新連接
            ListNode next_group_start = start_of_group.next;
            prev_group.next = prev_node;
            start_of_group.next = end_of_group; // 確保組尾部指向下一組的開頭
            
            // 更新 prev_group 指針，為下一輪做準備
            prev_group = start_of_group;
        }

        return dummy.next;
    }
}