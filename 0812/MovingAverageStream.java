import java.util.*;

public class MovingAverageStream {
    private int size;
    private Queue<Integer> window;
    private PriorityQueue<Integer> maxHeap; // smaller half
    private PriorityQueue<Integer> minHeap; // larger half
    private Map<Integer, Integer> delayed;  // 延遲刪除
    private TreeMap<Integer, Integer> counts; // 支援 getMin/getMax
    private double sum;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.delayed = new HashMap<>();
        this.counts = new TreeMap<>();
        this.sum = 0;
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        counts.put(val, counts.getOrDefault(val, 0) + 1);

        // 插入 heap
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }
        balanceHeaps();

        // 若超過視窗大小，移除最舊的
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            counts.put(removed, counts.get(removed) - 1);
            if (counts.get(removed) == 0) counts.remove(removed);

            delayed.put(removed, delayed.getOrDefault(removed, 0) + 1);

            // 移除的元素可能在 heap 頂端，需要清理
            if (removed <= maxHeap.peek()) {
                prune(maxHeap);
            } else {
                prune(minHeap);
            }
            balanceHeaps();
        }

        return sum / window.size();
    }

    public double getMedian() {
        if (window.isEmpty()) return 0.0;
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public int getMin() {
        return counts.firstKey();
    }

    public int getMax() {
        return counts.lastKey();
    }

    // 平衡兩個 Heap
    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            prune(maxHeap);
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            prune(minHeap);
        }
    }

    // 清理 Heap 頂端已被標記刪除的元素
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.peek();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) delayed.remove(num);
            heap.poll();
        }
    }

    // 測試
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.67
        System.out.println(ma.next(5));   // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}
