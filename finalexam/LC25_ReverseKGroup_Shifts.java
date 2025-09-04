import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC25_ReverseKGroup_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String line = sc.hasNextLine() ? sc.nextLine().trim() : "";
        if (line.isEmpty()) return;
        String[] parts = line.split("\\s+");

        ListNode dummy = new ListNode(0), tail = dummy;
        for (String s : parts) {
            tail.next = new ListNode(Integer.parseInt(s));
            tail = tail.next;
        }

        ListNode head = reverseKGroup(dummy.next, k);

        // 輸出
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) System.out.print(" ");
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            ListNode kth = prev;
            for (int i = 0; i < k && kth != null; i++) kth = kth.next;
            if (kth == null) break; // 不足 k 個
            ListNode nextGroup = kth.next;
            // 反轉 [prev.next ... kth]
            ListNode a = prev.next, b = a.next;
            while (b != nextGroup) {
                ListNode tmp = b.next;
                b.next = a;
                a = b;
                b = tmp;
            }
            // 連接前後
            ListNode start = prev.next;
            prev.next = kth;
            start.next = nextGroup;
            prev = start;
        }
        return dummy.next;
    }
}
