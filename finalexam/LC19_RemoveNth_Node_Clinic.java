import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC19_RemoveNth_Node_Clinic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode dummy = new ListNode(0), tail = dummy;
        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        int k = sc.nextInt();
        ListNode head = removeNthFromEnd(dummy.next, k);
        // 輸出
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) System.out.print(" ");
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        // fast 先走 k 步
        for (int i = 0; i < k; i++) fast = fast.next;
        // 同步移動直到 fast 到尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // slow.next 就是要刪掉的節點
        slow.next = slow.next.next;
        return dummy.next;
    }
}
