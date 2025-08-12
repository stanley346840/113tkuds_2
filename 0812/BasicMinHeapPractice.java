public class BasicMinHeapPractice {
    private int[] heap;
    private int size;
    private int capacity;

    public BasicMinHeapPractice(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // 取得父節點索引
    private int parent(int i) { return (i - 1) / 2; }
    // 取得左子節點索引
    private int leftChild(int i) { return 2 * i + 1; }
    // 取得右子節點索引
    private int rightChild(int i) { return 2 * i + 2; }

    // 插入元素
    public void insert(int val) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = val;
        size++;
        heapifyUp(size - 1);
    }

    // 往上調整 (維持 min-heap 性質)
    private void heapifyUp(int index) {
        while (index > 0 && heap[parent(index)] > heap[index]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    // 取出最小值並移除
    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    // 往下調整
    private void heapifyDown(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // 查看最小值但不刪除
    public int getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    // 取得當前大小
    public int size() {
        return size;
    }

    // 是否為空
    public boolean isEmpty() {
        return size == 0;
    }

    // 交換兩個元素
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 測試
    public static void main(String[] args) {
        BasicMinHeapPractice mh = new BasicMinHeapPractice(10);
        mh.insert(5);
        mh.insert(3);
        mh.insert(8);
        mh.insert(1);

        System.out.println("Min: " + mh.getMin()); // 1
        System.out.println("Extract Min: " + mh.extractMin()); // 1
        System.out.println("Min: " + mh.getMin()); // 3
        System.out.println("Size: " + mh.size()); // 3
        System.out.println("Is Empty: " + mh.isEmpty()); // false
    }
}
