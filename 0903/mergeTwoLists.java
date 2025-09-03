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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 創建一個虛擬頭節點，簡化操作
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 遍歷兩個鏈結串列，直到其中一個為空
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            // 移動當前指針到新連接的節點
            current = current.next;
        }

        // 將剩餘的非空串列連接到新串列的末尾
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        // 返回新串列的頭部 (跳過虛擬頭節點)
        return dummy.next;
    }
}