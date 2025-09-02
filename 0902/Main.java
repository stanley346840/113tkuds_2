class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j] 表示 s[0..i-1] 和 p[0..j-1] 是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 初始化 p 可以匹配空字串的情況
        for (int j = 2; j <= n; j += 2) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == sc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    char prev = p.charAt(j - 2);
                    // 情況1: '*' 當作 0 次
                    dp[i][j] = dp[i][j - 2];
                    // 情況2: '*' 當作 >=1 次
                    if (prev == sc || prev == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}

// 測試程式
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "aa";
        String p1 = "a*";
        System.out.println(sol.isMatch(s1, p1)); // true

        String s2 = "mississippi";
        String p2 = "mis*is*p*.";
        System.out.println(sol.isMatch(s2, p2)); // false

        String s3 = "ab";
        String p3 = ".*";
        System.out.println(sol.isMatch(s3, p3)); // true
    }
}
