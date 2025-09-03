class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        // 遍歷 haystack 的每一個可能的開始位置
        for (int i = 0; i <= m - n; i++) {
            int j;
            // 遍歷 needle 的每一個字符，與 haystack 進行比較
            for (j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break; // 不匹配，中斷內層迴圈
                }
            }
            // 如果內層迴圈完整執行，表示完全匹配
            if (j == n) {
                return i; // 返回起始索引
            }
        }
        
        // 遍歷結束仍未找到，返回 -1
        return -1;
    }
}