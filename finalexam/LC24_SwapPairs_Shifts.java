import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC24_SwapPairs_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.hasNextLine() ? sc.nextLine().trim() : "";
        if (line.isEmpty()) return;

        String[] parts = line.split("\\s+");
        ListNode dummy = new ListNode(0), tail = dummy;
        for (String s : parts) {
            tail.next = new ListNode(Integer.parseInt(s));
            tail = tail.next;
        }

        ListNode head = swapPairs(dummy.next);

        // 輸出
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) System.out.print(" ");
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 移動 prev
            prev = a;
        }
        return dummy.next;
    }
}
