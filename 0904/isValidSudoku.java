import java.util.HashSet;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 使用 HashSet 數組來追蹤已出現的數字
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        // 初始化所有 HashSet
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // 遍歷整個數獨盤面
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char digit = board[i][j];

                // 如果是空單元格，則跳過
                if (digit == '.') {
                    continue;
                }

                // 計算當前單元格所屬的 3x3 小方格索引
                int boxIndex = (i / 3) * 3 + (j / 3);

                // 檢查行、列和小方格中是否已經存在該數字
                if (rows[i].contains(digit) || cols[j].contains(digit) || boxes[boxIndex].contains(digit)) {
                    return false;
                }

                // 如果不存在，則將數字加入集合
                rows[i].add(digit);
                cols[j].add(digit);
                boxes[boxIndex].add(digit);
            }
        }

        // 如果所有格子都合法
        return true;
    }
}
