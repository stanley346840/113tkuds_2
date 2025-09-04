import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC23_MergeKLists_Hospitals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            ListNode dummy = new ListNode(0), tail = dummy;
            while (true) {
                int v = sc.nextInt();
                if (v == -1) break;
                tail.next = new ListNode(v);
                tail = tail.next;
            }
            lists[i] = dummy.next;
        }

        ListNode merged = mergeKLists(lists);

        // 輸出
        ListNode cur = merged;
        while (cur != null) {
            System.out.print(cur.val);
            cur = cur.next;
            if (cur != null) System.out.print(" ");
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;
            if (minNode.next != null) pq.offer(minNode.next);
        }
        return dummy.next;
    }
}
