// 定義鏈表節點
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class addtwonumbers{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 虛擬頭節點
        ListNode current = dummy;
        int carry = 0; // 進位
        
        // 遍歷兩個鏈表，直到都結束且進位為0
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            
            int sum = val1 + val2 + carry; // 計算當前位總和
            carry = sum / 10;             // 更新進位
            current.next = new ListNode(sum % 10); // 建立新節點
            current = current.next;
            
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        return dummy.next; // 返回結果鏈表
    }
}
