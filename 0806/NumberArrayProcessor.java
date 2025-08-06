import java.util.*;

public class NumberArrayProcessor {

    // 1. 移除重複元素
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // 2. 合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) merged[k++] = arr1[i++];
            else merged[k++] = arr2[j++];
        }

        while (i < arr1.length) merged[k++] = arr1[i++];
        while (j < arr2.length) merged[k++] = arr2[j++];

        return merged;
    }

    // 3. 找出出現頻率最高的元素
    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        int mostFrequent = arr[0];

        for (int num : arr) {
            int count = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, count);

            if (count > maxFreq || (count == maxFreq && num < mostFrequent)) {
                maxFreq = count;
                mostFrequent = num;
            }
        }

        return mostFrequent;
    }

    // 4. 將陣列分割成兩個子陣列，總和相等或接近
    public static List<List<Integer>> splitArray(int[] arr) {
        Arrays.sort(arr);
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();
        int sum1 = 0, sum2 = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                part1.add(arr[i]);
                sum1 += arr[i];
            } else {
                part2.add(arr[i]);
                sum2 += arr[i];
            }
        }

        return Arrays.asList(part1, part2);
    }

    // 主程式測試
    public static void main(String[] args) {
        int[] array1 = {1, 2, 2, 3, 4, 4, 5};
        int[] array2 = {2, 4, 6, 8};
        int[] array3 = {1, 3, 3, 2, 2, 3, 4};

        System.out.println("原始陣列1: " + Arrays.toString(array1));
        System.out.println("去除重複後: " + Arrays.toString(removeDuplicates(array1)));

        System.out.println("\n合併排序陣列: " +
                Arrays.toString(mergeSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6})));

        System.out.println("\n陣列3中出現最頻繁的數字: " + findMostFrequent(array3));

        System.out.println("\n分割陣列1為兩部分：");
        List<List<Integer>> parts = splitArray(array1);
        System.out.println("子陣列1: " + parts.get(0));
        System.out.println("子陣列2: " + parts.get(1));
    }
}
