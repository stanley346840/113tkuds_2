import java.util.*;

public class M12_MergeKTimeTables {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = sc.nextInt();
            }
            lists.add(arr);
        }

        List<Integer> merged = mergeKLists(lists);
        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i == merged.size() - 1 ? "\n" : " "));
        }
    }

    static class Pair {
        int val;
        int idx; // 哪條 list
        int pos; // list 中的位置
        Pair(int v, int i, int p) { val = v; idx = i; pos = p; }
    }

    static List<Integer> mergeKLists(List<int[]> lists) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        List<Integer> res = new ArrayList<>();

        // 初始化每條 list 的第一個元素
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 0) {
                pq.add(new Pair(lists.get(i)[0], i, 0));
            }
        }

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            res.add(cur.val);
            int[] arr = lists.get(cur.idx);
            if (cur.pos + 1 < arr.length) {
                pq.add(new Pair(arr[cur.pos + 1], cur.idx, cur.pos + 1));
            }
        }

        return res;
    }
}
