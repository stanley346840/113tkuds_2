public class ValidMaxHeapChecker {

    public static boolean isMaxHeap(int[] arr) {
        int n = arr.length;

        // 遍歷所有非葉子節點 (最後一個非葉子節點索引是 (n-2)/2)
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 檢查左子節點
            if (left < n && arr[i] < arr[left]) {
                System.out.println("false (索引" + left + "的" + arr[left] + "大於父節點" + arr[i] + ")");
                return false;
            }
            // 檢查右子節點
            if (right < n && arr[i] < arr[right]) {
                System.out.println("false (索引" + right + "的" + arr[right] + "大於父節點" + arr[i] + ")");
                return false;
            }
        }
        System.out.println("true");
        return true;
    }

    public static void main(String[] args) {
        isMaxHeap(new int[]{100, 90, 80, 70, 60, 75, 65}); // true
        isMaxHeap(new int[]{100, 90, 80, 95, 60, 75, 65}); // false (索引3的95大於父節點90)
        isMaxHeap(new int[]{50}); // true
        isMaxHeap(new int[]{}); // true
    }
}
