import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap; // 小一半
    private PriorityQueue<Integer> minHeap; // 大一半
    private Map<Integer, Integer> delayed;  // 延遲刪除的元素

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];

        for (int i = 0; i < n; i++) {
            addNum(nums[i]);
            if (i >= k) { // 移除滑出視窗的元素
                removeNum(nums[i - k]);
            }
            if (i >= k - 1) { // 視窗形成後開始取中位數
                result[i - k + 1] = getMedian(k);
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= maxHeap.peek()) {
            prune(maxHeap);
        } else {
            prune(minHeap);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            prune(maxHeap);
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] arr1 = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(arr1, 3))); 
        // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] arr2 = {1,2,3,4};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(arr2, 2))); 
        // [1.5, 2.5, 3.5]
    }
}
