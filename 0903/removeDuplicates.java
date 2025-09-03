class Solution {
    public int removeDuplicates(int[] nums) {
        // 處理數組為空的情況
        if (nums.length == 0) {
            return 0;
        }
        
        // 慢指針，標記不重複元素的末尾
        int i = 0;

        // 快指針，遍歷整個數組
        for (int j = 1; j < nums.length; j++) {
            // 如果快指針指向的元素與慢指針指向的元素不同，
            // 則它是一個新的不重複元素
            if (nums[j] != nums[i]) {
                // 移動慢指針
                i++;
                // 將新的不重複元素賦值到慢指針的位置
                nums[i] = nums[j];
            }
        }
        
        // 不重複元素的數量是 i + 1
        return i + 1;
    }
}