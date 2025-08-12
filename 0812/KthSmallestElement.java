import java.util.*;

public class KthSmallestElement {

    // 方法 1: Max Heap (保留 K 個最小元素)
    public static int kthSmallest_MaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    // 方法 2: Min Heap (提取 K 次)
    public static int kthSmallest_MinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        int[] arr2 = {1};
        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};

        System.out.println("方法1(Max Heap)：");
        System.out.println(kthSmallest_MaxHeap(arr1, 3)); // 7
        System.out.println(kthSmallest_MaxHeap(arr2, 1)); // 1
        System.out.println(kthSmallest_MaxHeap(arr3, 4)); // 3

        System.out.println("\n方法2(Min Heap)：");
        System.out.println(kthSmallest_MinHeap(arr1, 3)); // 7
        System.out.println(kthSmallest_MinHeap(arr2, 1)); // 1
        System.out.println(kthSmallest_MinHeap(arr3, 4)); // 3
    }
}
