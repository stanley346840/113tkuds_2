import java.util.Arrays;

public class SelectionSortImplementation {

    // --- 選擇排序 ---
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        System.out.println("【選擇排序】每一輪排序結果：");

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // 交換
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }

            System.out.printf("第 %d 輪：%s\n", i + 1, Arrays.toString(arr));
        }

        System.out.println("選擇排序總比較次數：" + comparisons);
        System.out.println("選擇排序總交換次數：" + swaps);
    }

    // --- 氣泡排序 ---
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        System.out.println("【氣泡排序】每一輪排序結果：");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    // 交換
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            System.out.printf("第 %d 輪：%s\n", i + 1, Arrays.toString(arr));

            // 若無交換，提前結束
            if (!swapped) break;
        }

        System.out.println("氣泡排序總比較次數：" + comparisons);
        System.out.println("氣泡排序總交換次數：" + swaps);
    }

    // --- 主程式 ---
    public static void main(String[] args) {
        int[] original = {64, 25, 12, 22, 11};

        // 測試選擇排序
        int[] arr1 = Arrays.copyOf(original, original.length);
        selectionSort(arr1);
        System.out.println("排序結果：" + Arrays.toString(arr1));

        System.out.println("\n===========================\n");

        // 測試氣泡排序
        int[] arr2 = Arrays.copyOf(original, original.length);
        bubbleSort(arr2);
        System.out.println("排序結果：" + Arrays.toString(arr2));
    }
}
