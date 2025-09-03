import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    /**
     * 回溯函數，用於生成所有有效括號組合
     * @param result 儲存所有有效組合的列表
     * @param currentString 當前正在建構的字串
     * @param openCount 已使用的左括號數量
     * @param closeCount 已使用的右括號數量
     * @param n 總共的括號對數
     */
    private void backtrack(List<String> result, String currentString, int openCount, int closeCount, int n) {
        // 終止條件：當左右括號的數量都達到 n 時，找到一個有效解
        if (currentString.length() == 2 * n) {
            result.add(currentString);
            return;
        }

        // 遞歸步驟 1: 添加左括號
        // 只要左括號數量沒有超過 n，就可以添加
        if (openCount < n) {
            backtrack(result, currentString + "(", openCount + 1, closeCount, n);
        }

        // 遞歸步驟 2: 添加右括號
        // 只有當右括號數量小於左括號數量時，才可以添加
        if (closeCount < openCount) {
            backtrack(result, currentString + ")", openCount, closeCount + 1, n);
        }
    }
}