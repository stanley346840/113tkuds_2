import java.util.*;

public class M01_BuildHeap {
    static int n;
    static boolean isMax;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        isMax = type.equals("max");
        buildHeap(arr);

        // 輸出
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
    }

    static void buildHeap(int[] arr) {
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
    }

    static void heapify(int[] arr, int i) {
        int best = i;
        int left = 2*i + 1, right = 2*i + 2;

        if (left < n && compare(arr[left], arr[best])) best = left;
        if (right < n && compare(arr[right], arr[best])) best = right;

        if (best != i) {
            int tmp = arr[i]; arr[i] = arr[best]; arr[best] = tmp;
            heapify(arr, best);
        }
    }

    static boolean compare(int a, int b) {
        return isMax ? a > b : a < b;
    }
}
