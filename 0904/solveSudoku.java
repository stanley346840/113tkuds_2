class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (backtrack(board)) {
                                return true; // 已經解出來
                            }
                            board[i][j] = '.'; // 回溯，清掉重試
                        }
                    }
                    return false; // 1-9 都不行，回溯
                }
            }
        }
        return true; // 沒有空格，代表成功解完
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false; // 檢查行
            if (board[i][col] == c) return false; // 檢查列
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) return false; // 檢查小方格
        }
        return true;
    }
}
