import java.util.Arrays;

class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 步驟 1: 從右向左，找到第一個非升序的元素
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 如果找到了支點（即 i >= 0）
        if (i >= 0) {
            int j = nums.length - 1;
            // 步驟 2: 從右向左，找到第一個大於 nums[i] 的元素
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 交換 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 步驟 3: 反轉從 i + 1 到數組末尾的子數組
        // 如果沒有找到支點，i 會是 -1，反轉會從 0 開始
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}