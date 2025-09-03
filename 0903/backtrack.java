import java.util.ArrayList;
import java.util.List;

class Solution {
    // 數字到字母的映射表
    private String[] mapping = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    private List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        // 處理空輸入情況
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backtrack(digits, 0, new StringBuilder());
        return result;
    }

    /**
     * 回溯函數，用於生成組合
     * @param digits 輸入的數字字串
     * @param index 當前處理的數字在字串中的索引
     * @param currentCombination 當前已生成的字母組合
     */
    private void backtrack(String digits, int index, StringBuilder currentCombination) {
        // 基本情況：如果組合長度等於數字字串長度，表示已完成一個組合
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        // 取得當前數字所對應的字母
        String letters = mapping[digits.charAt(index) - '0'];

        // 遞歸步驟：遍歷所有可能的字母
        for (int i = 0; i < letters.length(); i++) {
            // 附加當前字母
            currentCombination.append(letters.charAt(i));
            
            // 遞歸呼叫，處理下一個數字
            backtrack(digits, index + 1, currentCombination);
            
            // 回溯：移除剛才附加的字母，以便探索其他組合
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }
}