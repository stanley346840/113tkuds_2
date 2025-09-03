import java.util.PriorityQueue;
import java.util.Comparator;

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
    public ListNode mergeKLists(ListNode[] lists) {
        // 檢查特殊情況
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 創建一個最小堆來存儲節點，基於節點的 val 值進行排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

        // 將每個鏈結串列的頭節點加入優先佇列
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 當優先佇列不為空時，重複以下步驟
        while (!pq.isEmpty()) {
            // 從優先佇列中取出最小的節點
            ListNode minNode = pq.poll();
            
            // 將最小節點連接到合併後的鏈結串列
            current.next = minNode;
            current = current.next;

            // 如果該節點還有下一個節點，將其加入優先佇列
            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        return dummy.next;
    }
}