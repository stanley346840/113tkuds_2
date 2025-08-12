import java.util.*;

class Element {
    int value;
    int arrayIndex; // 這個值來自第幾個陣列
    int elementIndex; // 這個值在該陣列的索引位置

    public Element(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}

public class MergeKSortedArrays {
    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();

        // Min Heap 根據 value 排序
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        // 初始化：每個陣列放第一個元素
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        // 取出最小值，並加入該陣列的下一個元素
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < arrays[current.arrayIndex].length) {
                minHeap.offer(new Element(
                        arrays[current.arrayIndex][nextIndex],
                        current.arrayIndex,
                        nextIndex
                ));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1,4,5}, {1,3,4}, {2,6}};
        int[][] arr2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] arr3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(arr1)); // [1, 1, 2, 3, 4, 4, 5, 6]
        System.out.println(mergeKSortedArrays(arr2)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(mergeKSortedArrays(arr3)); // [0, 1]
    }
}
