import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC21_MergeTwoLists_Clinics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();

        ListNode dummy = new ListNode(0), tail = dummy;

        ListNode l1 = null, l1Tail = null;
        for (int i = 0; i < n; i++) {
            ListNode node = new ListNode(sc.nextInt());
            if (l1 == null) l1 = node;
            else l1Tail.next = node;
            l1Tail = node;
        }

        ListNode l2 = null, l2Tail = null;
        for (int i = 0; i < m; i++) {
            ListNode node = new ListNode(sc.nextInt());
            if (l2 == null) l2 = node;
            else l2Tail.next = node;
            l2Tail = node;
        }

        ListNode merged = mergeTwoLists(l1, l2);
        while (merged != null) {
            System.out.print(merged.val);
            merged = merged.next;
            if (merged != null) System.out.print(" ");
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;
        return dummy.next;
    }
}
