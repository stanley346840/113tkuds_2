import java.util.*;

public class M11_HeapSortWithTie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        // 穩定排序：可以用 Arrays.sort 包裝索引
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        Arrays.sort(idx, (a, b) -> {
            return Integer.compare(scores[a], scores[b]);
        });

        for (int i = 0; i < n; i++) {
            System.out.print(scores[idx[i]] + (i == n - 1 ? "\n" : " "));
        }
    }
}

